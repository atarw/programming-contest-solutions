#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

N, M, K = map (int, stdin.readline ().split ())

if K < N:
	print K + 1, 1
elif K < N + M:
	print N, K - N + 2
else:
	K -= (N + M - 1)	
	row = K // (M - 1) + 2
	
	K -= (M - 1) * (row - 2)
	col = 0
	
	if row % 2 == 0:
		col = M - K
	else:
		col = 2 + K
	
	print N - row + 1, col