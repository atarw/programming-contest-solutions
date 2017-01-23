from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

par = 0

for n in xrange (N):
	if arr [n] % 2 == n % 2:
		par += 1

print par