#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

MAXE = 100000
cnt, dp = [0] * (MAXE + 1), [0] * (MAXE + 1)

# input
N = int (stdin.readline ())
cards = map(int, stdin.readline ().split ())

for card in cards:
	cnt[card] += 1

# key observations:
# - if you take a card with number x, you might as well take ALL cards with number x
# - order in which you take cards in the optimal solution doesn't matter
#
# let dp[i] be the maximum value you get by taking cards with values in the range [0, i]
# to calculate dp[i], you have two choices: take all cards with value i or don't

dp[1] = cnt[1]

for i in xrange (2, MAXE + 1):
	dp[i] = max (dp[i - 1], cnt[i] * i + dp[i - 2])

print dp[MAXE]
