from __future__ import division
from sys import stdin

A, B, C = int (stdin.readline ()), 0, 0

if A % 2 == 0:
	B = (A // 2) * (A // 2) - 1
	C = B + 2
else:
	B = (A * A - 1) // 2
	C = B + 1

if A <= 2:
	print -1
else:
	print B, C