from sys import stdin, stdout
import itertools

arr = map(int, stdin.readline ().split ())
cnt = 0

for comb in itertools.combinations(arr, 3):
	A = comb[0]
	B = comb[1]
	C = comb[2]
	
	if not (A + B <= C or A + C <= B or B + C <= A):
		cnt += 1

print (cnt)