#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

A, B, C, D = map (int, stdin.readline ().split ())
score1 = max (3 * A // 10, A - C * A // 250)
score2 = max (3 * B // 10, B - D * B // 250)

if score1 > score2:
	print 'Misha'
elif score1 < score2:
	print 'Vasya'
else:
	print 'Tie'