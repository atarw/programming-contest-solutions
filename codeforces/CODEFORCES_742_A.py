#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())

if N == 0:
	print 1
else:
	print [8, 4, 2, 6][(N - 1) % 4]