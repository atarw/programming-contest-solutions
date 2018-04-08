#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

def damage (P):
	c, d = 1, 0
	
	for p in P:
		if p == 'C':
			c *= 2
		else:
			d += c
	
	return d

T = int (stdin.readline ())

for t in xrange (T):
	D, P = stdin.readline ().split ()
	D, P = int (D), list (P)
	
	cpy = sorted ([p for p in P], reverse=True)
	
	stdout.write ('Case #{}: '.format (t + 1))

	if damage (cpy) > D:
		print 'IMPOSSIBLE'
	else:
		ans = 0
		
		while damage (P) > D:
			for i in xrange (len (P) - 1, -1, -1):
				if P[i] == 'S':
					if P[i - 1] == 'S':
						continue
					else:
						P[i], P[i - 1] = P[i - 1], P[i]
						ans += 1
						break

		print ans