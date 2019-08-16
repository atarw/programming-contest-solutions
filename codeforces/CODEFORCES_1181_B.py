#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

L = int(stdin.readline())
N = stdin.readline().strip('\n\r')

left, right = L // 2, L // 2 + 1

while 0 <= left and N[left] == '0':
	left -= 1

while right < L and N[right] == '0':
	right += 1

ans1, ans2 = maxint, maxint

if left != 0:
	lnum1, lnum2 = int(N[:left]), int(N[left:])
	ans1 = lnum1 + lnum2

if right != L:
	rnum1, rnum2 = int(N[:right]), int(N[right:])
	ans2 = rnum1 + rnum2

print min(ans1, ans2)