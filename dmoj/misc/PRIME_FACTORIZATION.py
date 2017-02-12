from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

T = int (stdin.readline ())

for t in xrange (T):
	N = int (stdin.readline ())
	ori = N
	factors = []
	
	for n in xrange (2, N + 1):
		if n * n > ori:
			break
		
		while N % n == 0:
			N /= n
			factors.append (n)
	
	if N != 1:
		factors.append (int (N))
	
	factors.sort ()
	
	for f in factors:
		print f,
	print