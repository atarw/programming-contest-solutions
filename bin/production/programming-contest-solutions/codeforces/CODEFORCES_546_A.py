from sys import stdin
from sys import stdout

k, n, w = map (int, stdin.readline ().split ())
m = k * w * (w + 1) / 2

print (m - n if m > n else 0)