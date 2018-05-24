#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

N, A, B = map (int, stdin.readline ().split ())
N *= 6

lim = int (math.ceil (math.sqrt (N)))
newa, newb = A, B

for f in xrange (min (A, B), lim + 1):
	other = int (math.ceil (N / f))

	if newa < newb:
		f, other = max (A, min (f, other)), max (B, f, other)
	else:
		f, other = max (A, f, other), max (B, min (f, other))

	if N <= f * other and (newa * newb < N or f * other <= newa * newb):
		newa, newb = f, other

print newa * newb
print newa, newb