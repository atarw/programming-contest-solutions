from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N, C = map (int, stdin.readline ().split ())
times = map (int, stdin.readline ().split ())

cnt, time = 1, times [0]

for n in xrange (1, N):
	if times [n] - time > C:
		cnt = 1
	else:
		cnt += 1
	
	time = times [n]

print cnt