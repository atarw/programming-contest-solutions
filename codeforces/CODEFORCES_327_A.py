#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

ans = 0
totones, totzeros = arr.count (1), arr.count (0)

for n in xrange (N):
	ones, zeros = 0, 0
	
	for n2 in xrange (n, N):
		if arr[n2] == 0:
			zeros += 1
		elif arr[n2] == 1:
			ones += 1

		ans = max (ans, zeros + totones - ones)

print ans