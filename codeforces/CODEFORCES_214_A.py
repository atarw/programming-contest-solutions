#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N, M = map(int, stdin.readline().split())
cnt = 0

for a in xrange(max(N, M) + 1):
	b = N - a * a
	
	if 0 <= b and a + b * b == M:
		cnt += 1

print cnt