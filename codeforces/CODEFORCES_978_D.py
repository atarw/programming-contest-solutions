#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

if N == 1:
	print 0
else:
	best = maxint
	
	for i in xrange (-1, 2, 1):
		for j in xrange (-1, 2, 1):
			first = arr[0] + i
			second = arr[1] + j
			
			diff = first - second
			changes = abs (i) + abs (j)
			last = second
			overallGood = True
			
			for n in xrange (2, N):
				good = False
				
				for k in xrange (-1, 2, 1):
					if last - (arr[n] + k) == diff:
						changes += abs (k)
						good = True
						last = arr[n] + k
						break
				
				if not good:
					overallGood = False
					break
			
			if overallGood:
				best = min (best, changes)

	print -1 if best == maxint else best