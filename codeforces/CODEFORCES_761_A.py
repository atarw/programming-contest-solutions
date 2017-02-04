from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

A, B = map (int, stdin.readline ().split ())

if (abs (A - B) <= 1 and abs (A + B) > 0):
	print 'YES'
else:
	print 'NO'