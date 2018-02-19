#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

twentyfive, fifty = 0, 0
good = True 

for e in arr:
	if e == 25:
		twentyfive += 1
	elif e == 50:
		if twentyfive > 0:
			twentyfive -= 1
			fifty += 1
		else:
			good = False
	else:
		if fifty > 0 and twentyfive > 0:
			fifty -= 1
			twentyfive -= 1
		elif twentyfive > 2:
			twentyfive -= 3
		else:
			good = False

print 'YES' if good else 'NO'