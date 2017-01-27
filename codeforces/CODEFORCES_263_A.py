import sys

x = 0
y = 0

for row in xrange (0, 5):
	ln = sys.stdin.readline ().split ()
	
	if '1' in ln:
		x = ln.index ('1')
		y = row
		break
		
print (abs (x - 2) + abs (y - 2))