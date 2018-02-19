#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

N, M = map (int, stdin.readline ().split ())
energy = map (int, stdin.readline ().split ())
adj = [[] for n in xrange (N)]

for m in xrange (M):
	u, v = map (lambda x: int(x) - 1, stdin.readline ().split ())
	adj[u].append (v)
	adj[v].append (u)

fsum = [0] * N

for u in xrange (N):
	for v in adj[u]:
		fsum[u] += energy[v]

toremove = [n for n in xrange (N)]
toremove.sort(key = lambda x: energy[x])

ans = 0

# remove nodes with most energy first
while len (toremove):
	rm = toremove.pop ()
	ans += fsum[rm]
	
	for v in adj[rm]:
		fsum[v] -= energy[rm]

print ans