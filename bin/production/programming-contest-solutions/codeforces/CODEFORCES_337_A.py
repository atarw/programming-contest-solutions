from sys import stdin
from sys import stdout

N, M = map (int, stdin.readline ().split ())
f = map (int, stdin.readline ().split ())

f.sort ()

B = 0
diff = 1000000

while (B + N <= M):
	A = B + N - 1
		
	diff = min (diff, f [A] - f [B])
	A += 1
	B += 1

print (0 if diff == 1000000 else diff)