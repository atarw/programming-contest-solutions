#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N, Q = map (int, stdin.readline ().split ())
diff = [0] * (N + 1)

for q in xrange (Q):
	x, y = map (int, stdin.readline ().split ())
	
	diff[x] += 1
	diff[y] -= 1

blue = 1 if diff[0] > 0 else 0

for n in xrange (1, N + 1):
	diff[n] += diff[n - 1]
	
	if diff[n] > 0:
		blue += 1

print N - blue, blue