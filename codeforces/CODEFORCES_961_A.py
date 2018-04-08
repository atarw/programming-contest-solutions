#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

N, M = map (int, stdin.readline ().split ())
cols = map (int, stdin.readline ().split ())

arr = [0] * N

for c in cols:
	arr[c - 1] += 1

print min(arr)