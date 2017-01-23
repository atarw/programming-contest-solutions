from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

D = int (stdin.readline ())
dimes = map (int, stdin.readline ().split ())

Q = int (stdin.readline ())
quarters = map (int, stdin.readline ().split ())

dimes.sort (), quarters.sort ()

if 10 / dimes [len (dimes) - 1] > 25 / quarters [0]:
	print 'Dimes are better'
elif 25 / quarters [len (quarters) - 1] > 10 / dimes [0]:
	print 'Quarters are better'
else:
	print 'Neither coin is better'