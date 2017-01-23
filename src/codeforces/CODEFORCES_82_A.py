from sys import stdin
from sys import stdout

#2^i * 5 <= N, where i is the iteration and N is the number of cycles

N = int (stdin.readline ())
exp = 1

#too lazy to use log
while (exp * 5 <= N):
	N -= exp * 5
	exp *= 2

print ((['Sheldon', 'Leonard', 'Penny', 'Rajesh', 'Howard']) [(N - 1) / exp])