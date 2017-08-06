#!/usr/bin/python
import os, fnmatch

root = '/Users/atharva/Movies/Avatar The Last Airbender'
ext = ['*.m4v']
to_rename = []

for dir_name, sub_dir, files in os.walk (root):
	for e in ext:
		for f in fnmatch.filter (files, e):
			to_rename.append ((dir_name, f))

for f in to_rename:
	tok = f [1].split ()
	
	tok [0] = 'S' + tok [0][0:1] + 'E' + tok[0][1:]
	last = len (tok) - 1
	
	l = tok [last].rfind ('-')
	r = tok [last].rfind ('.m4v')
	
	tok [last] = tok [last][0:l] + tok [last][r:]
	
	oldpath = os.path.join (f [0], f [1])
	newpath = os.path.join (f [0], ' '.join (tok).strip ())
		
	os.rename (oldpath, newpath)
	
print 'DONE'