#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())
top = stdin.readline ().strip ('\n')
bot = stdin.readline ().strip ('\n')
good = False

for n in xrange (N):
	if top[n] == 'O' and bot[n] == 'O':
		good = True

print ':)' if good else ':('