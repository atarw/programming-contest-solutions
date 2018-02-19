#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

K = int (stdin.readline ())
ln = stdin.readline ().strip ('\n\r')

occ = dict ()

for c in ln:
	if c in occ:
		occ[c] += 1
	else:
		occ[c] = 1

good = True

if len (ln) % K != 0:
	good = False

length = len (ln) // K

for k in occ.keys ():
	if occ[k] % K == 0:
		occ[k] //= K
	else:
		good = False

if sum (occ.values ()) != length:
	good = False

if good:
	kstring = ''
	
	for k, v in occ.items ():
		kstring += k * v

	print kstring * K
else:
	print -1