import sys

ln = sys.stdin.readline ()

numbers = []
plus = 0

for i in ln:
	if i != '+' and i != '\n':
		numbers.append (int (i))
	elif i == '+':
		plus += 1

numbers.sort ()

for i in numbers:
	sys.stdout.write (str (i))
	
	if (plus > 0):
		sys.stdout.write ('+')
		plus -= 1