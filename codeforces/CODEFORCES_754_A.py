from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split())

s, e = 0, len (arr)
ranges = []
good = True

while s != len (arr):
	ss = sum (arr [s:e])
	
	while 0 < e and ss == 0:
		ss -= arr [e - 1]
		e -= 1
	
	if ss == 0:
		good = False
		break
	else:
		ranges.append ([s, e])
		s = e
		e = len (arr)

if good:
	print 'YES'
	print len (ranges)
	
	for r in ranges:
		print r [0] + 1, r [1]
else:
	print 'NO'