#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N, K = map (int, stdin.readline ().split ())
teams = []

for n in xrange (N):
	teams.append (map (int, stdin.readline ().split ()))

# too lazy to write a real comparator function
teams.sort (key = lambda x: x[1])
teams.sort (key = lambda x: x[0], reverse = True)

i = 0

while i < len (teams):
	j = i + 1
	eq = 1
	
	while j < len (teams) and teams[i] == teams[j]:
		j += 1
		eq += 1
	
	if i + 1 <= K <= j:
		print eq
		break
	
	i += 1