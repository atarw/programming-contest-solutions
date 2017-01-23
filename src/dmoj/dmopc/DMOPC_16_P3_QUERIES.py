from sys import stdin

T = int (stdin.readline ())

for t in xrange (T):
	A, B = 0, 0
	N, M = map (int, stdin.readline ().split ())
	
	for m in xrange (M):
		a, b = map (int, stdin.readline ().split ())
		A, B = max (A, a), max (B, b)
		
	if A + B > N:
		print -1
	else:
		ln = ''
		
		for a in xrange (A):
			ln += 'a'
		
		for b in xrange (B):
			ln += 'b'
		
		print ln