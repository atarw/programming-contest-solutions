#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

if N == 1:
	print -1
else:
	arr.sort ()

	mindiff = maxint

	for n in xrange (N - 1):
		mindiff = min (mindiff, arr[n + 1] - arr[n])
	
	i, j, cnt = 0, 0, 0

	for n in xrange (N - 1):
		if arr[n + 1] - arr[n] != mindiff:
			cnt += 1
			i, j = n, n + 1
	
	ans = set ()
	
	if cnt == 0:
		ans.add (arr[0] - mindiff)
		ans.add (arr[N - 1] + mindiff)

		if N == 2 and (arr[1] - arr[0]) % 2 == 0:
			ans.add (arr[0] + (arr[1] - arr[0]) // 2)
	elif cnt == 1:
		insertion = arr[i] + mindiff

		if insertion + mindiff == arr[j]:
			ans.add (insertion)
	
	print len (ans)
	lst = sorted (list (ans))

	for e in lst:
		stdout.write (str (e) + ' ')