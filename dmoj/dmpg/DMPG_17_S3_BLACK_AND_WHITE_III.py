#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

MOD = 1000000007
K, M = map (int, stdin.readline ().split ())

size = pow (2, K)
cnt = 0

for k in xrange (size):
	ln = stdin.readline ().split ()
	
	for l in ln:
		if l == '#':
			cnt += 1

ways = pow (pow (2, pow (4, M, MOD - 1), MOD) - 1, cnt, MOD)
print ways