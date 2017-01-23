from sys import stdin
from sys import stdout

def gcd (a, b):
	return a if b == 0 else gcd (b, a % b)

A, B, N = map (int, stdin.readline ().split ())
turn = 1

while 1:
	N -= (gcd (A, N) if turn == 1 else gcd (B, N))

	if N < 0:
		break
		
	turn ^= 1

print turn