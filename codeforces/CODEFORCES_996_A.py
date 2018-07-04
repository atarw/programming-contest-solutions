#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

N = int (stdin.readline ())
den = [100, 20, 10, 5, 1]
cnt = 0

while N > 0:
	for d in den:
		cnt += (N // d)
		N %= d

print cnt