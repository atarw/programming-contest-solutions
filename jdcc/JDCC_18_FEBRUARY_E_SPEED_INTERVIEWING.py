#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

R, C, Q = map (int, stdin.readline ().split ())
bit = [[0 for c in xrange (C + 1)] for r in xrange (R + 1)]
rating = [[0 for c in xrange (C)] for r in xrange (R)]

def update (r, c, v):
	while r < R + 1:
		c2 = c
		
		while c2 < C + 1:
			bit[r][c2] += v
			c2 += c2 & -c2
		
		r += r & -r

def query (r, c):
	ret = 0
	
	while r > 0:
		c2 = c
		
		while c2 > 0:
			ret += bit[r][c2]
			c2 -= c2 & -c2
		
		r -= r & -r
	
	return ret

def query_rect (r1, c1, r2, c2):
	return query (r2, c2) - query (r2, c1) - query (r1, c2) + query (r1, c1)

for r in xrange (R):
	ln = map (int, stdin.readline ().split ())
	
	for c in xrange (C):
		rating[r][c] = ln[c]
		update (r + 1, c + 1, ln[c])

for q in xrange (Q):
	cmd = stdin.readline ().split ()
	r, c, s = int (cmd[1]), int (cmd[2]), int (cmd[3])
	
	if cmd[0] == 'U':
		update (r, c, s - rating[r - 1][c - 1])
		rating[r - 1][c - 1] = s
	else:
		side = 0
		best = 0
		diff = maxint

		while r + side <= R and c + side <= C:
			sm = query_rect (r - 1, c - 1, r + side, c + side)
			
			if abs (sm - s) <= diff:
				diff = abs (sm - s)
				best = max (best, side)
				side += 1
			else:
				break

		print best + 1