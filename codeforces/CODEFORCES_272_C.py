#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

M = int (stdin.readline ())
last = 0

for m in xrange (M):
	w, h = map (int, stdin.readline ().split ())
	
	res = max (last, arr[w - 1])
	print res
	
	last = res + h