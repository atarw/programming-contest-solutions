#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

N = int (stdin.readline ())
ln = stdin.readline ().strip ('\n\r')

occ = dict ()

for n in xrange (N - 1):
	sub = ln [n : n + 2]
	
	if sub in occ:
		occ[sub] += 1
	else:
		occ[sub] = 1

max = -1
maxkey = ''

for key in iter (occ):
	if occ[key] > max:
		max = occ[key]
		maxkey = key

print maxkey