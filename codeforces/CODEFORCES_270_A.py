from sys import stdin

T = int (stdin.readline ())

for t in xrange (T):
	a = int (stdin.readline ())
	print 'NO' if a >= 180 or 360 % (a - 180) else 'YES'