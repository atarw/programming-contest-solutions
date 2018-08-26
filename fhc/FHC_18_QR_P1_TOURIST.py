#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

T = int (stdin.readline ())

for t in xrange (T):
	N, K, V = map (int, stdin.readline ().split ())
	places = [(n, stdin.readline ().strip ('\r\n')) for n in xrange (N)]
	
	NV = ((V - 1) * K) % N
	seen = sorted ([places[(NV + k) % N] for k in xrange (K)])
	
	stdout.write ('Case #{}:'.format (t + 1))
	
	for place in seen:
		stdout.write (' ' + place[1])
	
	stdout.write ('\n')