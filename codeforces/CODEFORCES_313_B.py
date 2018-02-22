#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

ln = stdin.readline ().strip ('\n\r')
arr = [0] * (len (ln) + 1)

for n in xrange (0, len (ln)):
	arr[n + 1] = arr[n]
	
	if ln[n] == ln[n - 1]:
		arr[n + 1] += 1

M = int (stdin.readline ())

for m in xrange (M):
	l, r = map (int, stdin.readline ().split ())
	print arr[r] - arr[l]