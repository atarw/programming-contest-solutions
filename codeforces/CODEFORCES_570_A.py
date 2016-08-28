from sys import stdin

# get input
N, M = map (int, stdin.readline ().split ())
city_votes = []

for m in range (M):
	city_votes.append (list (map (int, stdin.readline ().split ())))

# find winner in each city
city_winner = []

for city in range (M):
	city_winner.append (city_votes [city].index (max (city_votes [city])))

# find most frequent winner
freq = [0] * N

for winner in city_winner:
	freq [winner] += 1

print (freq.index (max (freq)) + 1)