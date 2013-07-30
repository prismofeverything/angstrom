# angstrom

A 3d molecular interaction playground for javascript

![ATP](http://imgur.com/CSMxME8)

## view molecules from PDB (the Protein Data Bank):  http://www.rcsb.org/pdb/home/home.do

Right now angstrom reads files in .pdb or .sdf format (sufficient for most protiens and ligands).  

To test, start up a server in the angstrom root:  

```
% python -m SimpleHTTPServer 22222
```

And navigate to http://localhost:22222 !

You should see an ATP molecule.  Use the scroll wheel to zoom in or the arrow keys and I/O (for "in" or "out") to navigate around.