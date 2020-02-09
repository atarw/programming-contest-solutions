#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

T = int(stdin.readline().strip('\r\n'))

for t in xrange(T):
	a, b = map(int, stdin.readline().split())
	diff = abs(b - a)
	
	fives = diff // 5
	diff %= 5
	
	twos = diff // 2
	diff %= 2
	
	ones = diff
	
	print(fives + twos + ones)