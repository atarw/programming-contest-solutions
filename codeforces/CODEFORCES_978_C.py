#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N, M = map (int, stdin.readline ().split ())
rms = map (int, stdin.readline ().split ())
letters = map (int, stdin.readline ().split ())

psa = [0] * (N + 1)

for n in xrange (N):
	psa[n + 1] = psa[n] + rms[n]

for letter in letters:
	idx = bisect.bisect_right (psa, letter)
	
	if letter == psa[idx - 1]:
		idx -= 1
	
	print idx, letter - psa[idx - 1]