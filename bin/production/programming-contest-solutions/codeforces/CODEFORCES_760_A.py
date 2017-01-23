from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

m, d = map (int, stdin.readline ().split ())
col, end = 1, 30

if m == 1 or m == 3 or m == 5 or m == 7 or m == 8 or m == 10 or m == 12:
	end = 31
elif m == 2:
	end = 28

d -= 1

for n in xrange (0, end):
	col += d // 7
	d %= 7
	d += 1

print col