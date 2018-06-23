#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

N, M = map (int, stdin.readline ().split ())
arr = map (int, stdin.readline ().split ())
prints = set (map (int, stdin.readline ().split ()))

for a in arr:
	if a in prints:
		stdout.write (str (a) + ' ')