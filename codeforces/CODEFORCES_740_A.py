#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N, A, B, C = map (int, stdin.readline ().split ())

if N % 4 == 0:
	print 0
elif (N + 1) % 4 == 0:
	print min (A, 3 * C, C + B)
elif (N + 2) % 4 == 0:
	print min (2 * A, B, 2 * C)
elif (N + 3) % 4 == 0:
	print min (3 * A, A + B, C)