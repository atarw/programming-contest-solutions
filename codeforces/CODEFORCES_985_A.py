#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())
arr.sort ()

# try odd (black) or even (white)
ans1, ans2 = 0, 0
i, j = 1, 2

for a in arr:
	ans1 += abs (a - i)
	i += 2
	
	ans2 += abs (a - j)
	j += 2

print min (ans1, ans2)