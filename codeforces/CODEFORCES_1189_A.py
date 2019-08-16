#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())
S = stdin.readline().strip('\n\r')

zeros, ones = 0, 0

for s in S:
	if s == '1':
		ones += 1
	else:
		zeros += 1

if zeros != ones:
	print 1
	print S
else:
	print 2
	print S[0], S[1:]