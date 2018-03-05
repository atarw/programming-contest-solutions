#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())
arr2 = sorted (arr)

incorrect = 0

for e, e2 in zip (arr, arr2):
	if e != e2:
		incorrect += 1

print 'YES' if incorrect <= 2 else 'NO'