#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N = int (stdin.readline ())
laptops = []

for n in xrange (N):
	laptops.append (map (int, stdin.readline ().split ()))

laptops.sort (key = lambda x: x[0])
mx = laptops[0][1]
good = False

for n in xrange (N):
	if mx > laptops[n][1]:
		good = True
	
	mx = max (mx, laptops[n][1])

print 'Happy Alex' if good else 'Poor Alex'