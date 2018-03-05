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
# let dp[i] be the maximum value you get by taking cards with values in the range [i, MAXE]
# to calculate dp[i], you have two choices: take all cards with value i or don't

dp[MAXE] = MAXE * cnt[MAXE]
dp[MAXE - 1] = (MAXE - 1) * cnt[MAXE - 1]

for i in xrange (MAXE - 2, -1, -1):
	dp[i] = max(dp[i + 1], cnt[i] * i + dp[i + 2])

print dp[0]