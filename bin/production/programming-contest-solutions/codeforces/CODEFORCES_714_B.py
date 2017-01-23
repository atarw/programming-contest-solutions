from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

s = set (arr)

if len (s) == 1 or len (s) == 2:
	print 'YES'
elif len (s) >= 4:
	print 'NO'
else:
	new_arr = list (s)
	new_arr.sort ()
	
	if 2 * new_arr [1] == new_arr [0] + new_arr [2]:
		print 'YES'
	else:
		print 'NO'