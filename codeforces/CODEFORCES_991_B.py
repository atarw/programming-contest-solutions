#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string
from decimal import *

N = int (stdin.readline ())
grades = sorted (map (int, stdin.readline ().split ()))
cnt = 0

while Decimal (sum (grades)) / Decimal (N) < Decimal (4.5):
	grades[0] = 5
	cnt += 1
	grades.sort ()

print cnt