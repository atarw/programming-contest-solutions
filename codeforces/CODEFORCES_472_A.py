from sys import stdin
from sys import stdout

N = int (stdin.readline ())

if N % 2 == 0:
	print 4, N - 4
else:
	print min (9, N - 9), max (9, N - 9)