from sys import stdin

N = int (stdin.readline ())

w1, w2 = 0, 0

for n in xrange (N):
	m, c = map (int, stdin.readline ().split ())
	
	if m > c:
		w1 += 1
	elif c > m:
		w2 += 1
	else:
		pass
		
if w1 > w2:
	print 'Mishka'
elif w2 > w1:
	print 'Chris'
else:
	print 'Friendship is magic!^^'