#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

N = int (stdin.readline ())
arr = sorted (map (int, stdin.readline ().split ()))

print arr[(N - 1) // 2]