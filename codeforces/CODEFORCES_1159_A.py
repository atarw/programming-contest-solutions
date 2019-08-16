#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())
S = stdin.readline().strip('\n\r')

change = 0
mn = 0

for c in S:
	if c == '-':
		change -= 1
	else:
		change += 1
	
	mn = min(mn, change)

print abs(mn) + change