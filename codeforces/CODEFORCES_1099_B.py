#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())
best = 0

for i in xrange(N + 1):
	if i * i <= N:
		best = i
	else:
		break

nonsquares = N - best * best
rows = int(math.ceil(nonsquares / best))
print 2 * best + rows