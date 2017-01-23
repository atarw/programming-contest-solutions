from __future__ import division
from sys import stdin

r, f = list (stdin.readline ().strip ('\n'))

if r + f == 'a8' or r + f == 'h8' or r + f == 'a1' or r + f == 'h1':
	print 3
elif r == 'a' or r == 'h' or f == '1' or f == '8':
	print 5
else:
	print 8