from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

s = set ()
best = 0

for elem in arr:
	if elem in s:
		s.remove (elem)
	else:
		s.add (elem)
	
	best = max (best, len (s))

print best