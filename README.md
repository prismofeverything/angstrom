# angstrom

A 3d molecular interaction playground for javascript

![ATP](http://i.imgur.com/CSMxME8.png)

## View molecules from PDB (Protein Data Bank)

Angstrom pulls molecule descriptions from http://www.rcsb.org/pdb/home/home.do

Right now angstrom reads files in .pdb or .sdf format (sufficient for most protiens and ligands).  

To test, start up a server in the angstrom root:  

```
% python -m SimpleHTTPServer 22222
```

And navigate to your own floating [ATP molecule](http://localhost:22222)!

Use the scroll wheel to zoom in or the arrow keys and I/O (for "in" or "out") to navigate around.