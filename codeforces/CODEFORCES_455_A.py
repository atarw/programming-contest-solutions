#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect

MAXE = 100000
cnt, dp = [0] * (MAXE + 1), [0] * (MAXE + 1)

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

for e in arr:
	cnt[e] += 1

dp[1] = cnt[1]

for i in xrange (2, MAXE + 1):
    dp[i] = max (dp[i - 1], cnt[i] * i + dp[i - 2])

print dp[MAXE]