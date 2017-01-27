from __future__ import division
from sys import stdin

N, drinks = int (stdin.readline ()), map (int, stdin.readline ().split ())
print sum (drinks) / N