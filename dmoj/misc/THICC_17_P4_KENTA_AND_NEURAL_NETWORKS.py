#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

s1 = stdin.readline ().strip ()
I = len (s1)
N = int (stdin.readline ())
best = 1e10

for n in xrange (N):
	tok = stdin.readline ().split ()
	t = int (tok [0])
	sm = 0
	
	for tt in xrange (t):
		J = len (tok[tt + 1])
		dp = [[0 for j in xrange (J + 1)] for i in xrange (I + 1)]
				
		for j in xrange (J + 1):
			dp[0][j] = j
			
		for i in xrange (I + 1):
			dp[i][0] = i
			
		for i in xrange (1, I + 1):
			for j in xrange (1, J + 1):
				if s1[i - 1] == tok[tt + 1][j - 1]:
					dp[i][j] = dp[i - 1][j - 1]
				else:
					dp[i][j] = 1 + min (dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
		
		sm += dp[I][J]
	
	best = min (best, sm / t)

print best