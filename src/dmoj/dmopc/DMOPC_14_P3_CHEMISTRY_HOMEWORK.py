from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N, M = int (stdin.readline ()), int (stdin.readline ())
matrix = [[0] * N for n in xrange (N)]

# check if atom is valid hydrogen atom
def h (n):
	bonds = sum (matrix [n])
	
	if bonds != 1:
		return False
	
	# must be connected to carbon atom
	return c (matrix [n].index (1))

# check if atom is valid carbon atom
def c (n):
	bonds = sum (matrix [n])
	
	if bonds != 4:
		return False
	
	for n2 in xrange (N):
		if matrix [n][n2] > 2:
			return False
	
	return True

# get input
for m in xrange (M):
	a, b = map (int, stdin.readline ().split ())
	matrix [a - 1][b - 1] += 1
	matrix [b - 1][a - 1] += 1

# validate graph
valid = True

for n in xrange (N):
	if not c (n) and not h (n):
		valid = False
		break

if valid:
	# find bond energy
	energy = 0
	
	for n in xrange (N):
		for n2 in xrange (N):
			# bond exists
			if matrix [n][n2]:
				if c (n):
					if c (n2):
						# C=C
						if matrix [n][n2] == 2:
							energy += 615
						# C-C
						elif matrix [n][n2] == 1:
							energy += 346
					#C-H
					elif h (n2):
						energy += 413
				elif h (n):
					#H-C
					if c (n2):
						energy += 413
	
	energy //= 2;			
	
	# find formula
	C, H = 0, 0
	
	for n in xrange (N):
		if c (n):
			C += 1
		elif h (n):
			H += 1
	
	print energy
	print 'C' + (str (C) if C > 1 else '') + 'H' + (str (H) if H > 1 else '')
else:
	print 'Impossible'