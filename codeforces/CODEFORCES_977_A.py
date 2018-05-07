#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math

N, K = map (int, stdin.readline ().split ())
lst = []

while True:
	lst.append (N % 10)
	N //= 10
	
	if N == 0:
		break

lst.reverse ()

while K > 0:
	K -= 1
	
	if lst[len (lst) - 1] > 0:
		lst[len (lst) - 1] -= 1
	else:
		lst.pop ()

for i in lst:
	stdout.write (str (i))