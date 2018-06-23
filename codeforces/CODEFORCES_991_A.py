#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

A, B, C, N = map (int, stdin.readline ().split ())
good = True

if C > A or C > B:
	print -1
else:
	A -= C
	B -= C
	
	if A + B + C > N:
		print -1
	else:
		if N - A - B - C < 1:
			print -1
		else:
			print N - A - B - C