#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N, K = map (int, stdin.readline ().split ())

for k in xrange (K + 1, 0, -1):
	stdout.write (str (k) + ' ')

for k in xrange (K + 2, N + 1, 1):
	stdout.write (str (k) + ' ')