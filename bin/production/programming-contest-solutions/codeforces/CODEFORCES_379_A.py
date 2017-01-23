from __future__ import division
from sys import stdin

candles, b = map (int, stdin.readline ().split ())
hrs, burnt = 0, 0

while candles > 0:
	hrs += candles
	burnt += candles
	
	candles = burnt // b
	burnt %= b
		
print hrs