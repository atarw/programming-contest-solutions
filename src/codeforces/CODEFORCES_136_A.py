from sys import stdin
from sys import stdout

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ());#ith friend gave gift to arr [i]
arr2 = [0] * N

for n in xrange (0, N):
	arr2 [arr [n] - 1] = n + 1
	
for n in arr2:
	stdout.write (str (n) + " ")