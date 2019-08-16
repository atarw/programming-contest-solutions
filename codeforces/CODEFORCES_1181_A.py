#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

X, Y, Z = map(int, stdin.readline().split())

tot = (X + Y) // Z
gave = 0

if tot == (X // Z) + (Y // Z):
	pass
else:
	needx = Z - (X % Z)
	needy = Z - (Y % Z)
	
	if needx <= needy and Y >= needx:
		gave = needx
	elif needy < needx and X >= needy:
		gave = needy

print tot, gave