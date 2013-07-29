function ajaxRequest(url, callback) {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4) {
      callback(xhr.responseText);
    }
  }
  xhr.open('GET', url, true);
  xhr.send(null);
}

function safeInt(s) {
  var f = parseInt(s);
  return isNaN(f) ? null : f;
}

function safeFloat(s) {
  var f = parseFloat(s);
  return isNaN(f) ? null : f;
}

var PDB = function () {
  function fetch(id, callback) {
    ajaxRequest('http://www.rcsb.org/pdb/files/' + id + '.pdb', callback);
  }

  function parse(pdb) {
    var lines = pdb.split('\n');
    var segments = [];
    var line, atom, atoms = [];

    for (var l = 0; l < lines.length; l++) {
      line = lines[l];
      if (line.slice(0, 4) === 'ATOM') {
        atom = {
          number: safeInt(line.slice(6, 11)),
          label: line.slice(12, 16).trim(),
          alternateLocation: line.slice(16, 17).trim(),
          residue: line.slice(17, 20).trim(),
          chain: line.slice(21, 22).trim(),
          sequence: line.slice(22, 26).trim(),
          insertion: safeInt(line.slice(26, 27)),
          x: safeFloat(line.slice(30, 38)),
          y: safeFloat(line.slice(38, 46)),
          z: safeFloat(line.slice(46, 54)),
          occupancy: safeFloat(line.slice(54, 60)),
          temperature: safeFloat(line.slice(60, 66)),
          segment: line.slice(72, 76).trim(),
          element: line.slice(76, 78).trim(),
          charge: line.slice(78, 80).trim()
        }
        atoms.push(atom);
      } else if (line.slice(0, 3) === 'TER') {
        segments.push(atoms);
        atoms = [];
      }
    }

    if (atoms.length > 0) {
      segments.push(atoms);
    }

    return segments;
  }

  return {
    fetch: fetch,
    parse: parse
  }
} ();

var SDF = function() {
  function fetch(id, callback) {
    ajaxRequest('http://www.rcsb.org/pdb/files/' + id + '.sdf', callback);
  }

  function parse(sdf) {
    var lines = sdf.split('\n').slice(3);
    var header = lines[0];
    var data = lines.slice(1);
    var atomsCount = safeInt(header.slice(0,3));
    var bondsCount = safeInt(header.slice(3,6));
    var atomData = data.slice(0, atomsCount);
    var bondData = data.slice(atomsCount, atomsCount + bondsCount + 1);
    var atom, data, atoms = [];

    for (var a = 0; a < atomsCount; a++) {
      data = atomData[a];
      atom = {
        number: a+1,
        element: data.slice(30, 32).trim(),
        x: safeFloat(data.slice(0, 10)),
        y: safeFloat(data.slice(10, 20)),
        z: safeFloat(data.slice(20, 30))
      }

      atoms.push(atom);
    }

    return [atoms];
  }

  return {
    fetch: fetch,
    parse: parse
  }
} ();
