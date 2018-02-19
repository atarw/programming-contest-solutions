#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout
import math

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

prime = [True] * pow (10, 6)
squared_primes = set ()

for i in xrange (2, len (prime)):
	if prime[i]:
		squared_primes.add (i * i)
		for j in xrange (i * 2, len (prime), i):
			prime[j] = False

for e in arr:
	if e in squared_primes:
		print 'YES'
	else:
		print 'NO'