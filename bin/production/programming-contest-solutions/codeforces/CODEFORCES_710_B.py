from __future__ import division
from sys import stdin

N = int (stdin.readline ())
pos = map (int, stdin.readline ().split ())
pos.sort ()

print pos [(N - 1) // 2]