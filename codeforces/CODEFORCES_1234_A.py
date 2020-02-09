#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

Q = int(stdin.readline())

for q in xrange(Q):
	N = int(stdin.readline())
	arr = map(int, stdin.readline().split(' '))
	sm = sum(arr)
	
	print sm // N if sm % N == 0 else (sm + (N - (sm % N))) // N