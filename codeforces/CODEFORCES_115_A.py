#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N = int (stdin.readline ())
graph = [[] for n in xrange (N)]
roots = []

for n in xrange (N):
	parent = int (stdin.readline ())
	
	if parent != -1:
		graph[parent - 1].append (n)
	else:
		roots.append (n)

def dfs (u):
	depth = 1
	
	for v in graph[u]:
		depth = max (depth, 1 + dfs (v))
	
	return depth

groups = 0

for root in roots:
	groups = max (groups, dfs (root))

print groups