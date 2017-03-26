from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N, K = map (int, stdin.readline ().split ())
total = 0

while True:
	drank = N - N % K
	got = drank // K
	N = N - drank + got
	total += drank
	
	if drank == 0:
		total += N
		break

print total