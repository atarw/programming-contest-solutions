from sys import stdin, stdout

N = int (stdin.readline ())

for n in xrange (N):
	if n % 2 == 0:
		stdout.write ('I hate ')
	else:
		stdout.write ('I love ')
	
	if n == N - 1:
		stdout.write ('it')
	else:
		stdout.write ('that ')