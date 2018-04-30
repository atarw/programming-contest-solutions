#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

N = int (stdin.readline ())
ln = stdin.readline ().strip ('\n\r')

zeros, ones = 0, 0

for c in ln:
	if c == '0':
		zeros += 1
	else:
		ones += 1


if ones:
	stdout.write ('1' + '0' * zeros)
else:
	stdout.write ('0' * zeros)