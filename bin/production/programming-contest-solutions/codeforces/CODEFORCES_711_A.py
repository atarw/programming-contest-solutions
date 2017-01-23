from sys import stdin

N = int (stdin.readline ())
seats = []

possible = False

for n in range (N):
	seats.append (stdin.readline ().strip ('\n'))
	
	if not possible and 'OO' in seats [n]:
		possible = True
		seats [n] = seats [n].replace ('OO', '++', 1)

if possible:
	print ('YES')
	
	for row in seats:
		print (row)
	
else:
	print ('NO')