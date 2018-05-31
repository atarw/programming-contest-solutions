#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

x, y = map (int, stdin.readline ().split ())

# x^y > y^x
# y*ln(x) > x*ln(y)
# y/ln(y) > x/ln(x)

if x == y:
	print '='
elif x == 1 or y == 1:
	print '>' if x > y else '<'
elif x / math.log (x) > y / math.log (y):
	print '<'
elif x / math.log (x) < y / math.log (y):
	print '>'
else:
	print '='