#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

ln = list (stdin.readline ().strip ('\n'))
ln2 = list (stdin.readline ().strip ('\n'))
ln3 = list (stdin.readline ().strip ('\n'))
ln3cpy = list (ln3)

for c in ln3:
	if c in ln:
		ln.remove (c)
		ln3cpy.remove (c)
	elif c in ln2:
		ln2.remove (c)
		ln3cpy.remove (c)

print 'NO' if len (ln) or len (ln2) or len (ln3cpy) else 'YES'