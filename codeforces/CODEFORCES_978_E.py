#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N, W = map (int, stdin.readline ().split ())
arr = map (int, stdin.readline ().split ())

changes = [0] * N
cur = 0

for n in xrange (N):
	cur += arr[n]
	changes[n] = cur

mn = min (changes)
mx = max (changes)

if mn >= 0:
	print max (0, W - mx + 1)
elif mx <= 0:
	print max (0, W - abs (mn) + 1)
else:
	print max (0, W - mx - abs (mn) + 1)