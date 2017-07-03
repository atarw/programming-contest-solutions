#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N, M = map (int, stdin.readline ().split ())
parent, sz, edges = [n for n in xrange (N)], [1] * N, [0] * N

def find (u):
	if u != parent [u]:
		parent [u] = find (parent [u])
	
	return parent [u]

def union (u, v):
	r1, r2 = find (u), find (v)

	if r1 != r2:
		parent [r1] = r2
		sz [r2] += sz [r1]
		edges [r2] += edges [r1]
	
	edges [r2] += 1

for m in xrange (M):
	u, v = map (int, stdin.readline ().split ())
	union (u - 1, v - 1)

valid = True

for n in xrange (N):
	if sz [find (n)] * (sz [find (n)] - 1) // 2 != edges [find (n)]:
		valid = False
		break

print 'YES' if valid else 'NO'