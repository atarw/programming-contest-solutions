import sys

N = int (sys.stdin.readline ())
count = 0
cap = 0
ppl = 0

for n in xrange (0, N):
	ppl, cap = map (int, sys.stdin.readline ().split ())
	
	if ppl + 2 <= cap:
		count += 1
		
print (count)