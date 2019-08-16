#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N, M, K = map(int, stdin.readline().split())
print 'Yes' if M >= N and K >= N else 'No'
