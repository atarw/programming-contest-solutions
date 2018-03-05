#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

R, G, B = map (int, stdin.readline ().split ())

lo, hi = 0, min (R, G, B) + 1
best = 0

while lo < hi:
	mid = (lo + hi) // 2
	
	# make *mid* mixing bouquets
	newR, newG, newB = R - mid, G - mid, B - mid
	tot = (newR // 3) + (newG // 3) + (newB // 3) + mid
	
	if tot >= best:
		best = tot
		lo = mid + 1
	else:
		hi = mid

print best