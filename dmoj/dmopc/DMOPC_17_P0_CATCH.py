#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint

lst = []

for i in xrange (3):
	lst.append (map (int, stdin.readline ().split ()))

D = maxint

for i in xrange (len(lst)):
	for j in xrange (i + 1, len(lst)):
		D = min (D, pow (lst[i][0] - lst[j][0], 2) + pow (lst[i][1] - lst[j][1], 2))

print D