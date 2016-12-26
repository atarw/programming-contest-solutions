from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

l1, r1, l2, r2, k = map (int, stdin.readline ().split ())

if l2 > r1 or l1 > r2:
	print 0
elif max (l1, l2) <= k <= min (r1, r2):
	print min (r1, r2) - max (l1, l2)
else:
	print min (r1, r2) - max (l1, l2) + 1