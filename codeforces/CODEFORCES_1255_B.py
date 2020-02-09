#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

T = int(stdin.readline().strip('\r\n'))

for t in xrange(T):
	N, M = map(int, stdin.readline().split())
	arr = sorted(enumerate(map(int, stdin.readline().split())), key=lambda x: x[1])
	
	if M < N or N == 2:
		print -1
	else:		
		# N = M holds
		cost = 0
		edges = []
		
		l, r = 0, N - 1
		
		while l < r:
			cost += arr[l][1] + arr[r][1]
			edges.append((arr[l][0], arr[r][0]))
			l += 1
			r -= 1
		
		l, r = 1, N - 1
		
		while l < r:
			cost += arr[l][1] + arr[r][1]
			edges.append((arr[l][0], arr[r][0]))
			l += 1
			r -= 1
		
		cost += arr[0][1] + arr[N // 2][1]
		edges.append((arr[0][0], arr[N // 2][0]))
		
		print cost
		
		for edge in edges:
			print edge[0] + 1, edge[1] + 1