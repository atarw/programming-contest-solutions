from sys import stdin

T = int (stdin.readline ())

for t in xrange (T):
	N, P = map (int, stdin.readline ().split ())
	arr = map (int, stdin.readline ().split ())
	arr2 = [0] * (N + 1)
	
	for n in xrange (N):
		arr2 [n + 1] = arr2 [n] + arr [n]
	
	a, b, count = 0, 0, 0
	
	while a < N:
		while b < N and arr2 [b + 1] - arr2 [a] <= P:
			b += 1
			count += b - a
		
		a += 1
	
	print 'Case #%d: %d' % (t + 1, count)