#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

for n in xrange (N):
	cur = arr[n]
	
	occ = dict ()
	
	for n2 in xrange (N):
		if n2 == n:
			continue
		else:
			if arr[n2] in occ:
				occ[arr[n2]] += 1 
			else:
				occ[arr[n2]] = 1
	
	seq = [cur]
	left = N - 1
	
	while left > 0:
		nxt = 0
		
		if cur % 3 == 0 and (cur // 3) in occ:
			nxt = cur // 3
		elif (cur * 2) in occ:
			nxt = cur * 2
		else:
			break
	
		seq.append (nxt)
		cur = nxt
		left -= 1
		
		if occ[nxt] == 1:
			del occ[nxt]
		else:
			occ[nxt] -= 1
	
	if left == 0:
		for i in seq:
			stdout.write (str (i) + ' ')