from sys import stdin
import itertools

G = int (stdin.readline ())

for g in xrange (G):
	N = int (stdin.readline ())
	attack = map (int, stdin.readline ().split ())
	hp, hm = map (int, stdin.readline ().split ())
	ans = 'NOT LETHAL'
	
	# remove duplicate orders of attack
	permute = set (itertools.permutations (attack))
	
	for p in permute:
		hpt, hmt = hp, hm
		pos = 0
		
		while hpt > 0 and pos < N:
			hpt -= p [pos]
			pos += 1
		
		while hmt > 0 and pos < N:
			hmt -= p [pos]
			pos += 1
		
		if hpt <= 0 and hmt <= 0:
			ans = 'LETHAL'
			break
		
	print ans