#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())
ans = 0

for n in xrange (1, N, 1):
	if (N - n) % n == 0:
		ans += 1

print ans