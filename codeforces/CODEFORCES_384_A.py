from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N = int (stdin.readline ())
board = [[0] * N for n in xrange (N)]
cnt = 0

for n in xrange (N):
	if n % 2 == 0:
		for n2 in xrange (N):
			board [n][n2] = 'C' if n2 % 2 == 0 else '.'
			
			if n2 % 2 == 0:
				cnt += 1
	else:
		for n2 in xrange (N):
			board [n][n2] = 'C' if n2 % 2 != 0 else '.'
			
			if n2 % 2 != 0:
				cnt += 1

print cnt

for bb in board:
	for b in bb:
		stdout.write (b)
	print