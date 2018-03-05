#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

msg = stdin.readline ().strip ('\n')
left = 0
valid = True

for c in msg:
	if c == '<':
		left += 1
	else:
		if left - 1 < 0:
			valid = False
		else:
			left -= 1

print '<3' if valid else '</3'