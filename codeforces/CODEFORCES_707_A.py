from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N, M = map (int, stdin.readline ().split ())
color = False

for n in xrange (N):
	for m in stdin.readline ().split ():
		if m != 'G' and m != 'B' and m != 'W':
			color = True

print '#Color' if color else '#Black&White'