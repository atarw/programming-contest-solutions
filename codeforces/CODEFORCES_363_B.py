#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N, K = map (int, stdin.readline ().split ())
arr = map (int, stdin.readline ().split ())

l, r, cur = 0, 0, 0

while r < K:
	cur += arr[r]
	r += 1

r -= 1
ans = cur
start = 0

while r + 1 < N:
	cur -= arr[l]
	cur += arr[r + 1]
	
	r += 1
	l += 1
	
	if cur < ans:
		ans = cur
		start = l

print start + 1