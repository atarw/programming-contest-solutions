import sys

N = int (sys.stdin.readline ())

cap = 0
curr = 0

for n in xrange (0, N):
	exit, enter = map (int, sys.stdin.readline ().split ())
	curr -= exit
	curr += enter
	
	cap = max (curr, cap)
	
print (cap)