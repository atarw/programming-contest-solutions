#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N, M = map (int, stdin.readline ().split ())
c = map (int, stdin.readline ().split ())
b = [0] * M
ans = 0

c.sort (reverse = True)

p = 0
for n in xrange (N):
	ans += pow(c[n], b[p], 1000000007)
	ans %= 1000000007
	b [p] += 1
	p += 1
	p %= M
	
print ans