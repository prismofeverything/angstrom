var atomic = function() {
  var defaultRadius = 1.0;

  var atomicProperties = {
    H: {
      name: 'Hydrogen',
      number: 1,
      color: 0xffffff,
      radius: 0.23
    },

    Li: {
      name: 'Lithium',
      number: 3,
      color: 0xb22222,
      radius: defaultRadius
    },

    Be: {
      name: 'Beryllium',
      number: 4,
      radius: defaultRadius
    },

    B: {
      name: 'Boron',
      number: 5,
      color: 0x00ff00,
      radius: 0.83
    },

    C: {
      name: 'Carbon',
      number: 6,
      color: 0xc8c8c8,
      radius: 0.68
    },

    N: {
      name: 'Nitrogen',
      number: 7,
      color: 0x8f8fff,
      radius: 0.68
    },

    O: {
      name: 'Oxygen',
      number: 8,
      color: 0xf00000,
      radius: 0.68
    },

    F: {
      name: 'Fluorine',
      number: 9,
      color: 0xdaa520,
      radius: 0.64
    },

    Mg: {
      name: 'Magnesium',
      number: 12,
      radius: defaultRadius
    },

    Al: {
      name: 'Aluminium',
      number: 13,
      color: 0x808090,
      radius: defaultRadius
    },

    Si: {
      name: 'Silicon',
      number: 14,
      color: 0xdaa520,
      radius: 1.20
    },

    P: {
      name: 'Phosphorus',
      number: 15,
      color: 0xffa500,
      radius: 1.05
    },

    S: {
      name: 'Sulfur',
      number: 16,
      color: 0xffc832,
      radius: 1.02
    },

    Cl: {
      name: 'Chlorine',
      number: 17,
      color: 0x00ff00,
      radius: 0.99
    },

    Ar: {
      name: 'Argon',
      number: 18,
      radius: defaultRadius
    },

    V: {
      name: 'Vanadium',
      number: 23,
      radius: defaultRadius
    },

    Cr: {
      name: 'Chromium',
      number: 24,
      color: 0x808090,
      radius: defaultRadius
    },

    Mn: {
      name: 'Manganese',
      number: 25,
      color: 0x808090,
      radius: defaultRadius
    },

    Co: {
      name: 'Cobalt',
      number: 27,
      radius: defaultRadius
    },

    Cu: {
      name: 'Copper',
      number: 29,
      color: 0xa52a2a,
      radius: defaultRadius
    },

    Zn: {
      name: 'Zinc',
      number: 30,
      color: 0xa52a2a,
      radius: defaultRadius
    },

    Ga: {
      name: 'Gallium',
      number: 31,
      radius: defaultRadius
    },

    As: {
      name: 'Arsenic',
      number: 33,
      radius: 1.21
    },

    Se: {
      name: 'Selenium',
      number: 34,
      radius: 1.22
    },

    Br: {
      name: 'Bromine',
      number: 35,
      color: 0xa52a2a,
      radius: 1.21
    },

    Kr: {
      name: 'Krypton',
      number: 36,
      radius: defaultRadius
    },

    Rb: {
      name: 'Rubidium',
      number: 37,
      radius: defaultRadius
    },

    Sr: {
      name: 'Strontium',
      number: 38,
      radius: defaultRadius
    },

    Y: {
      name: 'Yttrium',
      number: 39,
      radius: defaultRadius
    },

    Mo: {
      name: 'Molybdenum',
      number: 42,
      radius: defaultRadius
    },

    Ag: {
      name: 'Silver',
      number: 47,
      color: 0x808090,
      radius: defaultRadius
    },

    Cd: {
      name: 'Cadmium',
      number: 48,
      radius: defaultRadius
    },

      In: {
        name: 'Indium',
        number: 49,
        radius: defaultRadius
      },

    Sb: {
      name: 'Antimony',
      number: 51,
      radius: defaultRadius
    },

    Te: {
      name: 'Tellurium',
      number: 52,
      radius: 1.47
    },

    I: {
      name: 'Iodine',
      number: 53,
      color: 0xa020f0,
      radius: 1.40
    },

    Xe: {
      name: 'Xenon',
      number: 54,
      radius: defaultRadius
    },

    Cs: {
      name: 'Caesium',
      number: 55,
      radius: defaultRadius
    },

    Ba: {
      name: 'Barium',
      number: 56,
      color: 0xffa500,
      radius: defaultRadius
    },

    La: {
      name: 'Lanthanum',
      number: 57,
      radius: defaultRadius
    },

    Ce: {
      name: 'Cerium',
      number: 58,
      radius: defaultRadius
    },

    Sm: {
      name: 'Samarium',
      number: 62,
      radius: defaultRadius
    },

    Eu: {
      name: 'Europium',
      number: 63,
      radius: defaultRadius
    },

    Gd: {
      name: 'Gadolinium',
      number: 64,
      radius: defaultRadius
    },

    Tb: {
      name: 'Terbium',
      number: 65,
      radius: defaultRadius
    },

    Ho: {
      name: 'Holmium',
      number: 67,
      radius: defaultRadius
    },

    Yb: {
      name: 'Ytterbium',
      number: 70,
      radius: defaultRadius
    },

    Lu: {
      name: 'Lutetium',
      number: 71,
      radius: defaultRadius
    },

    W: {
      name: 'Tungsten',
      number: 74,
      radius: defaultRadius
    },

    Re: {
      name: 'Rhenium',
      number: 75,
      radius: defaultRadius
    },

    Os: {
      name: 'Osmium',
      number: 76,
      radius: defaultRadius
    },

    Ir: {
      name: 'Iridium',
      number: 77,
      radius: defaultRadius
    },

    Pt: {
      name: 'Platinum',
      number: 78,
      radius: defaultRadius
    },

    Au: {
      name: 'Gold',
      number: 79,
      color: 0xdaa520,
      radius: defaultRadius
    },

    Hg: {
      name: 'Mercury',
      number: 80,
      radius: defaultRadius
    },

    Tl: {
      name: 'Thallium',
      number: 81,
      radius: defaultRadius
    },

    Pb: {
      name: 'Lead',
      number: 82,
      radius: defaultRadius
    },

    U: {
      name: 'Uranium',
      number: 92,
      radius: defaultRadius
    }
  }

  for (var element in atomicProperties) {
    var color = atomicProperties[element].color;
    var radius = atomicProperties[element].radius;

    atomicProperties[element].material = new THREE.MeshPhongMaterial({color: color || 0xff1493});
    atomicProperties[element].geometry = new THREE.SphereGeometry(radius + 0.45, 16, 16);
  }

  function listElements(molecule) {
    var elements = {};
    for (var s = 0; s < molecule.length; s++) {
      console.log(molecule[s].length);
      for (var a = 0; a < molecule[s].length; a++) {
        elements[molecule[s][a].element] = true;
      }
    }

    var names = [];
    for (var e in elements) {
      names.push(e);
    }

    return names;
  }

  function atomicGeometry(segments) {
    var spheres = [], properties;
    var atoms, atom, sphere, material, geometry;
    var molecule = new THREE.Object3D();
    var segment = new THREE.Object3D();

    for (var s = 0; s < segments.length; s++) {
      atoms = segments[s];

      for (var a = 0; a < atoms.length; a++) {
        atom = atoms[a];
        properties = atomicProperties[atom.element];
        material = properties.material;
        geometry = properties.geometry;
        sphere = new THREE.Mesh(geometry, material);
        sphere.position.set(atom.x, atom.y, atom.z);
        segment.add(sphere);
      }

      molecule.add(segment);
    }

    return molecule;
  }

  return {
    atomicProperties: atomicProperties,
    atomicGeometry: atomicGeometry,
    listElements: listElements
  }
} ();
