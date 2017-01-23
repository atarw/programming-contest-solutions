import sys

N = int (sys.stdin.readline ())
coins = map(int, sys.stdin.readline ().split ())
coins.sort (reverse=True)

coin_sum = sum (coins)
curr = 0
removed = 0
count = 0

while curr <= coin_sum:
	removed = coins.pop (0)
	curr += removed
	coin_sum -= removed
	count += 1

print (count)