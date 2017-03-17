from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N, M = map (int, stdin.readline ().split ())
awake = 0

for n in xrange (N):
	arr = map (int, stdin.readline ().split ())
	
	for m in xrange (0, 2 * M, 2):
		if arr [m] or arr [m + 1]:
			awake += 1

print awake