#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

sums = [[0 for n in xrange (N + 1)] for i in xrange (2)]

for n in xrange (N):
	sums[0][n + 1] = sums[0][n] + arr[n]

arr.sort ()

for n in xrange (N):
	sums[1][n + 1] = sums[1][n] + arr[n]
	
M = int (stdin.readline ())

for m in xrange (M):
	t, l, r = map (int, stdin.readline ().split ())
	print sums[t - 1][r] - sums[t - 1][l - 1]