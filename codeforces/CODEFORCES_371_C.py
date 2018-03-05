#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

recipe = stdin.readline ().strip ('\n\r')
have = map (int, stdin.readline ().split ())
price = map (int, stdin.readline ().split ())
r = int (stdin.readline ())

cnt = [0] * 3

for c in recipe:
	if c == 'B':
		cnt[0] += 1
	elif c == 'S':
		cnt[1] += 1
	elif c == 'C':
		cnt[2] += 1

lo, hi = 0, max (have) + r + 1

while lo < hi:
	mid = (lo + hi) // 2
	
	# make *mid* sandwiches
	need = [mid * cnt[i] for i in xrange (3)]
	cur = [have[i] for i in xrange (3)]
	money = r
	
	for i in xrange (3):
		if cur[i] < need[i]:
			money -= (need[i] - cur[i]) * price[i]
	
	if money < 0:
		hi = mid
	else:
		lo = mid + 1

print lo - 1