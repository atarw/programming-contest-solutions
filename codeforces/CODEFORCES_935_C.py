#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout
import math

r, x, y, x1, y1 = map (int, stdin.readline ().split ())

if (x1 - x) * (x1 - x) + (y1 - y) * (y1 - y) >= r * r:
	print x, y, r
elif x1 == x and y1 == y:
	d1, d2 = x1 + r, y1
	m1, m2 = (d1 + x1) / 2, (d2 + y1) / 2
	r2 = math.sqrt ((d1 - m1) * (d1 - m1) + (d2 - m2) * (d2 - m2))
	print m1, m2, r2
else:
	# find furthest point to (x1, y1) on diameter
	v1, v2 = x1 - x, y1 - y
	magv = math.sqrt (v1 * v1 + v2 * v2)
	
	d11, d21 = x + v1 * r / magv, y + v2 * r / magv
	d12, d22 = x - v1 * r / magv, y - v2 * r / magv
	
	m11, m21 = (d11 + x1) / 2, (d21 + y1) / 2
	m12, m22 = (d12 + x1) / 2, (d22 + y1) / 2

	r1 = math.sqrt ((d11 - m11) * (d11 - m11) + (d21 - m21) * (d21 - m21))
	r2 = math.sqrt ((d12 - m12) * (d12 - m12) + (d22 - m22) * (d22 - m22))
	
	if r1 > r2:
		print m11, m21, r1
	else:
		print m12, m22, r2