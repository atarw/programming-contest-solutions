import sys

k = int (sys.stdin.readline ())
l = int (sys.stdin.readline ())
m = int (sys.stdin.readline ())
n = int (sys.stdin.readline ())
d = int (sys.stdin.readline ()) + 1
count = 0

for x in xrange (1, d):
	if x % k == 0 or x % l == 0 or x % m == 0 or x % n == 0:
		count += 1
	
print (count)