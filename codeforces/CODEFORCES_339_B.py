#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N, M = map (int, stdin.readline ().split ())
arr = map (int, stdin.readline ().split ())

cur, steps = 1, 0

for nxt in arr:
	if cur == nxt:
		continue
	elif cur < nxt:
		steps += nxt - cur
		cur = nxt
	else:
		steps += N - cur + nxt
		cur = nxt

print steps