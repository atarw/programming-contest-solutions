import sys

def lucky (n):
	rem = 0
	
	while n != 0:
		rem = n % 10
		n /= 10
		
		if rem != 4 and rem != 7:
			return False
			
	return True

n = int (sys.stdin.readline ())

if lucky (n):
	print ("YES")
else:
	good = False
	
	for i in xrange (3, n / 2 + 1):
		if (lucky (i) and n % i == 0):
			good = True
			break
	
	print ("YES" if good else "NO")			