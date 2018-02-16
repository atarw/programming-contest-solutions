#!/usr/bin/python
from __future__ import division

from sys import stdin, stdout

N = int (stdin.readline ())
grid = []

for n in xrange (N):
	grid.append (map (int, stdin.readline ().split ()))


def inc (lst):
	for n in xrange (1, N):
		if lst[n] <= lst[n - 1]:
			return False

	return True


def rotate ():
	cpy = []

	for n in xrange (N):
		cpy.append ([])

		for n2 in xrange (N):
			cpy[n].append (grid[n][n2])

	for n in xrange (N):
		for n2 in xrange (N):
			cpy[n2][N - n - 1] = grid[n][n2]

	return cpy


def valid ():
	for n in xrange (N):
		arr, arr2 = [], []

		for n2 in xrange (N):
			arr.append (grid[n2][n])
			arr2.append (grid[n][n2])

		if not inc (arr) or not inc (arr2):
			return False

	return True


for i in xrange (4):
	if valid ():
		for n in xrange (N):
			for n2 in xrange (N):
				stdout.write (str (grid[n][n2]) + (' ' if n2 != N - 1 else '\n'))
		break

	grid = rotate ()
