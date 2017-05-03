#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())
row, first = 1, 1
	
while first + row <= N:
	first += row
	row += 1
	
last = first + row - 1

print ((last * (last + 1)) // 2) - (((first - 1) * first) // 2)