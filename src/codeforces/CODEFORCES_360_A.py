from sys import stdin
from sys import stdout

N, D = map (int, stdin.readline ().split ())
curr = 0
cons = 0

for d in xrange (D):
	good = False
	ln = stdin.readline ().strip ("\n")
	
	for i in ln:
		if (i == '0'):
			good = True
			break
	
	if good:
		curr += 1
		cons = max (cons, curr)
	else:
		curr = 0

print (cons)