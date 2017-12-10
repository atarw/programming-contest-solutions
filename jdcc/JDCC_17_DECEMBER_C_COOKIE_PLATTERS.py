#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())
diff, moves = 0, 0

for a in arr:
	cur = diff
	
	if cur == a:
		continue
	elif cur > a:
		diff -= (cur - a)
		moves += 1
	else:
		diff += (a - cur)
		moves += 1

print moves