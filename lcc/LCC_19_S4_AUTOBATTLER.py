#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout
from fractions import Fraction
import bisect, collections, heapq, itertools, operator, math

# did dp and looked at the pattern of coefficients for the basic units you can buy with size 1...n, and tier 1
# turns out they are along the diagonal of pascal's triangle
# find the coefficients, multiply them by 1...n and sum it all up

N, K = map(int, stdin.readline().split())
MOD = 1000000007

def inv(x):
	return pow(x, MOD - 2, MOD)

c, ans = 1, N

for k in range(1, N):
	moddiv = ((K - 2 + k) * inv(k)) % MOD
	c = (c * moddiv) % MOD
	ans += (((N - k) * c) % MOD)
	ans %= MOD

print(ans)