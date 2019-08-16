#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())

print 4 * (N - 1) + 1 + 4 * (N - 2) * (N - 1) // 2