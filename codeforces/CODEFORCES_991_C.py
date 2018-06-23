#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

N = int (stdin.readline ())
low, high = 1, N + 1

while low < high:
	mid = (low + high) // 2
	curN, eaten = N, 0
	
	while curN > 0:
		eaten += min (mid, curN)
		curN -= min (mid, curN)
		curN -= (curN // 10)
	
	if eaten * 2 >= N:
		high = mid
	else:
		low = mid + 1

print low