#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

N = int (stdin.readline ())
stones = [('purple', 'Power'), ('green', 'Time'), ('blue', 'Space'), ('orange', 'Soul'), ('red', 'Reality'), ('yellow', 'Mind')]

for n in xrange (N):
	color = stdin.readline ().strip ('\r\n')
	
	for s in stones:
		if color == s[0]:
			stones.remove (s)
			break

print len (stones)

for s in stones:
	print s[1]