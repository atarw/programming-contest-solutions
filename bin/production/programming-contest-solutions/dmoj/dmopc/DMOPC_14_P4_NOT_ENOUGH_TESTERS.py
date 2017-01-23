from sys import stdin
from bisect import bisect_left

T = int (stdin.readline ())

sieve = [0] * 100001
sieve [1] = 1

k_list = [[] for k in xrange (100001)]
k_list [1] = [1]

for a in xrange (2, 100001):
	sieve [a] += 2 #1 and a are factors of a
	k_list [sieve [a]].append (a)
	
	for a2 in xrange (2 * a, 100001, a):
		sieve [a2] += 1 #factor of a

for t in xrange (T):
	K, A, B = map (int, stdin.readline ().split ())
	total = bisect_left (k_list [K], B + 1) - bisect_left (k_list [K], A)
	
	print total