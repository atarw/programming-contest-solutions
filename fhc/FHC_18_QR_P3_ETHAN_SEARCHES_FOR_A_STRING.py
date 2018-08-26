#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

T = int (stdin.readline ())

for t in xrange (T):
	A = stdin.readline ().strip ('\r\n')
	idx = A.find (A[0], 1)
	ans = 'Impossible'
	
	if idx != -1:
		B = A[0:idx] + A
		
		i, j = 0, 0
		found = True
				
		while True:			
			if i >= len (A):
				break
			
			if j >= len (B):
				found = False
				break
			
			if A[i] == B[j]:
				i += 1
				j += 1
			elif i == 0:
				j += 1
			else:
				i = 0
		
		if not found:
			ans = B
	
	print 'Case #{}: {}'.format (t + 1, ans)
			