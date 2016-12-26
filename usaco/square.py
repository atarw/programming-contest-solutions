from __future__ import division

from sys import stdin, stdout, maxint

from fractions import Fraction
import bisect, collections, heapq, itertools, operator

f_in = open ('square.in', 'r')
f_out = open ('square.out', 'w')

x1, y1, x2, y2 = map (int, f_in.readline ().split ())
x3, y3, x4, y4 = map (int, f_in.readline ().split ())
x = [x1, x2, x3, x4]
y = [y1, y2, y3, y4]

ans = pow (max (max (x) - min (x), max (y) - min (y)), 2)

f_out.write (str (ans))