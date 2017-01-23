from sys import stdin
import string

# (A * B) % M = ((A % M) * (B % M)) % M
# (A + B) % M = ((A % M) + (B % M)) % M

def fast_pow_mod (x, y, z):
	num = 1
	
	while y:
		if y & 1:
			num = num * x % z
		
		y >>= 1
		x = x * x % z
		
	return num

arr = [stdin.readline ().rstrip (), stdin.readline ().rstrip ()]
letters = dict (zip (string.letters, [ord (c) % 32 for c in string.letters]))

vals = [0, 0]

for n in xrange (2):
	for pos in xrange (len (arr [n])):
		vals [n] = ((vals [n] % 10) + (fast_pow_mod (letters [arr [n][pos]], pos + 1, 10) % 10)) % 10

	if vals [n] == 0:
		vals [n] = 10
	
print sum (vals)