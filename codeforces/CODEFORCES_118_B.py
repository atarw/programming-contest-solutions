#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())
spaces = 2 * N

# top
for n in xrange (N):
	for i in xrange (spaces - 1):
		stdout.write (' ')
	
	spaces -= 2
	
	for k in xrange (n + 1):
		stdout.write (' ' + str (k))
	
	for k in xrange (n - 1, -1, -1):
		stdout.write (' ' + str (k))
	
	print

# middle
for n in xrange (N):
	stdout.write (str (n) + ' ')

for n in xrange (N, -1, -1):
	stdout.write (str (n) + (' ' if n else '\n'))

# bottom
spaces = 2

for n in xrange (N - 1, -1, -1):
	for i in xrange (spaces - 1):
		stdout.write (' ')
	
	spaces += 2
	
	for k in xrange (n + 1):
		stdout.write (' ' + str (k))
	
	for k in xrange (n - 1, -1, -1):
		stdout.write (' ' + str (k))
	
	print