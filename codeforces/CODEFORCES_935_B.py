#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout
import math

N = int (stdin.readline ())
ln = stdin.readline ().strip ('\n\r')

x, y = 0, 0
ans = 0
side = -1

for c in ln:
	if c == 'U':
		if side == 0 and y == x:
			side = 1
			ans += 1
		elif side == -1 or side == 1:
			side = 1
		
		y += 1
		
	elif c == 'R':
		if side == 1 and x == y:
			side = 0
			ans += 1
		elif side == -1 or side == 0:
			side = 0
		
		x += 1

print ans