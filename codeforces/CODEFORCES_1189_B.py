#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

N = int(stdin.readline())
arr = sorted(map(int, stdin.readline().split()))

dq = collections.deque()
dq.append(arr[N - 1])

n = N - 2
left = True

while n >= 0:
	if left:
		dq.appendleft(arr[n])
	else:
		dq.append(arr[n])
	
	left = not left
	n -= 1

ans = list(dq)
good = True

for n in xrange(N):
	if ans[n] >= ans[(n - 1) % N] + ans[(n + 1) % N]:
		good = False
		break

if good:
	print 'YES'
	
	for i in ans:
		stdout.write(str(i) + ' ')
else:
	print 'NO'