from sys import stdin
from string import ascii_uppercase

ln = stdin.readline().split()

for i, j in enumerate(ln):
	print(j, end = ' ' if not (i == len(ln) - 1 or ln[i+1][0] in ascii_uppercase) else '')
	
	if i == len(ln) - 1 or ln[i+1][0] in ascii_uppercase:
		print('. ', end='')
	