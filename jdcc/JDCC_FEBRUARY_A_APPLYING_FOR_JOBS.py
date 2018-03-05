#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int(stdin.readline ())
ans = ''

for n in xrange (N):
	ln = stdin.readline().strip('\n')
	
	if len(ans) < len(ln):
		ans = ln

print ans