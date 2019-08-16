#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N, M = map (int, stdin.readline ().split ())

onestep, twostep = N % 2, N // 2
nxtmult = int(math.ceil(((onestep + twostep) / M))) * M

diff = nxtmult - (onestep + twostep)

# can't replace one twostep with two onesteps
if diff > twostep:
	print -1
else:
	twostep -= diff
	onestep += diff * 2
	
	print onestep + twostep