#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

lst = []
st = set ()

for n in xrange (N - 1, -1, -1):
	if arr[n] not in st:
		lst.append (arr[n])
		st.add (arr[n])

print len (lst)
lst.reverse ()
for i in lst:
	stdout.write (str (i) + ' ')