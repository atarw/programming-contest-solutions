#!/usr/bin/python
from __future__ import division

from sys import maxint, stdin

N = int (stdin.readline ())
arr = []

for n in xrange (N):
	arr.append (int (stdin.readline ()))

arr.sort ()

ans = maxint

for n in xrange (1, N - 1):
	ans = min (ans, (arr[n + 1] - arr[n - 1]) / 2)

print round (ans, 1)
