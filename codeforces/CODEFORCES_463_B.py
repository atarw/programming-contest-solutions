#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

N = int (stdin.readline ())
height = map (int, stdin.readline ().split ())

energy, cur, money = 0, 0, 0

for h in height:
	if energy + cur - h < 0:
		money += -energy - cur + h
		cur += -energy - cur + h
	
	energy += cur - h
	cur = h

print money