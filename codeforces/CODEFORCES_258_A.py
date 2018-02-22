#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

num = list (stdin.readline ().strip ('\n\r'))
skipped = False

for n in xrange (len (num)):
	if num[n] == '0' and not skipped:
		skipped = True
	elif not skipped and n == len (num) - 1:
		skipped = True
	else:
		stdout.write (num[n])