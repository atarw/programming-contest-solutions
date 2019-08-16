#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())
arr = map(int, stdin.readline().split())
k = maxint

for i in xrange (N):
	k = min(k, arr[i] // max(i, N - i - 1))

print k