#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

W, H = map(int, stdin.readline().split())
U1, D1 = map(int, stdin.readline().split())
U2, D2 = map(int, stdin.readline().split())

while H > 0:
	W += H
	
	if H == D1:
		W = max(0, W - U1)
	
	if H == D2:
		W = max(0, W - U2)
	
	H -= 1

print W