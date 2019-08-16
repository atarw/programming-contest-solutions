#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N, P, K = map(int, stdin.readline().split())
arr = map(int, stdin.readline().split())

# (i + j)(i^2 + j^2) = k (mod p)
# (i - j)(i + j)(i^2 + j^2) = (i - j)k (mod p)
# i^4 - ik = j^4 - jk (mod p)
seen = dict()

for a in arr:
	x = (pow(a, 4) - a * K) % P
	
	if x not in seen:
		seen[x] = 1
	else:
		seen[x] += 1

ans = 0

for v in seen.values():
	ans += v * (v - 1) // 2

print ans