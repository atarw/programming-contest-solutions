from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

W = int (stdin.readline ())
C = int (stdin.readline ())

if W == 3 and C >= 95:
	print 'C.C. is absolutely satisfied with her pizza.'
elif W == 1 and C <= 50:
	print 'C.C. is fairly satisfied with her pizza.'
else:
	print 'C.C. is very satisfied with her pizza.'