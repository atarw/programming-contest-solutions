#!/usr/bin/python

import os, fnmatch

root = '/Users/Atharva/Documents/Github/programming-contest-solutions'
ext = ['*.class', '*.java~']
to_remove = []

for dir_name, sub_dir, files in os.walk (root):
	for e in ext:
		for f in fnmatch.filter (files, e):
			to_remove.append (os.path.join (dir_name, f))

for f in to_remove:
	os.remove (f)