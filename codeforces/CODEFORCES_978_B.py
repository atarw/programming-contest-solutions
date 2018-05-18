#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N = int (stdin.readline ())
ln = stdin.readline ().strip ('\n\r')

ans = 0
cur = 0

for i in ln:
	if i == 'x':
		cur += 1
	else:
		if cur >= 3:
			ans += (cur - 2)
		cur = 0

if cur >= 3:
	ans += (cur - 2)
print ans