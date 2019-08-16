#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())

if N % 2 == 1:
	print 0
else:
	print pow(2, N // 2)