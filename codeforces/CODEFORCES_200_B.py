from __future__ import division
from sys import stdin

N, volume = int (stdin.readline ()), 0

for drink in map (int, stdin.readline ().split ()):
	volume += drink / N

print volume