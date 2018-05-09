#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

def factor (x):
	lim = int (math.ceil (math.sqrt (x)))
	ans = set ()

	for i in xrange (1, lim + 1):
		if x % i == 0:
			ans.add (i)
			ans.add (x // i)
	
	return ans

A, B = map (int, stdin.readline ().split ())
set1, set2 = factor (A), factor (B)

shared = []

for i in set1:
	if i in set2:
		shared.append (i)

shared.sort ()

Q = int (stdin.readline ())

for q in xrange (Q):
	lo, hi = map (int, stdin.readline ().split ())
	idx = bisect.bisect_right (shared, hi)
	print shared[idx - 1] if idx and shared[idx - 1] >= lo else -1