#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

T = int (stdin.readline ())

for t in xrange (T):
	P, Q = map (int, stdin.readline ().split ('/'))
	gcd = fractions.gcd (P, Q)
	
	P /= gcd
	Q /= gcd
	ans = 'impossible'
	
	if pow (2, int (math.log (Q, 2))) == Q:
		ans = 0
		
		while Q > P:
			Q /= 2
			ans += 1
	
	print 'Case #{}: {}'.format (t + 1, ans)