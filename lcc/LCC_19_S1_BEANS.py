#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())
c = map(int, stdin.readline().split())
d = map(int, stdin.readline().split())

print min(x // y for x, y in zip(c, d))
