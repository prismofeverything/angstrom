var PDB = function () {
  function safeInt(s) {
    var f = parseInt(s);
    return isNaN(f) ? null : f;
  }

  function safeFloat(s) {
    var f = parseFloat(s);
    return isNaN(f) ? null : f;
  }

  function fetch(id, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4) {
        callback(xhr.responseText);
      }
    }
    xhr.open('GET', 'http://www.rcsb.org/pdb/files/' + id + '.pdb', true);
    xhr.send(null);
  }

  function parse(pdb) {
    var lines = pdb.split("\n");
    var segments = [];
    var line, bits, atom, atoms = [];

    for (var l = 0; l < lines.length; l++) {
      line = lines[l];
      if (line.slice(0, 4) === "ATOM") {
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
      } else if (line.slice(0, 3) === "TER") {
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
