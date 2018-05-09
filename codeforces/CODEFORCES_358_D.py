#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

N = int (stdin.readline ())
A = map (int, stdin.readline ().split ())
B = map (int, stdin.readline ().split ())
C = map (int, stdin.readline ().split ())

prevunfed, prevfed = A[N - 1], B[N - 1]

for n in xrange (N - 2, -1, -1):
	newprevunfed = max (A[n] + prevfed, B[n] + prevunfed)
	newprevfed = max (B[n] + prevfed, C[n] + prevunfed)
	prevfed, prevunfed = newprevfed, newprevunfed

print prevunfed