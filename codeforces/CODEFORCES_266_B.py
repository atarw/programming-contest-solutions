from sys import stdin
from sys import stdout

N, T = map (int, stdin.readline ().split ())
ln = [c for c in stdin.readline ()]

for t in xrange (0, T):
	n = 0
	
	while (n < N - 1):
		if ln [n] < ln [n + 1]:
			ln [n], ln [n + 1] = ln [n + 1], ln [n]
			n += 2
		else:
			n += 1

for i in ln:
	stdout.write (i)