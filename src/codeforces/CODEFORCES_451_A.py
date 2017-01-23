from sys import stdin

N, M = map (int, stdin.readline ().split ())
print "Akshat" if min (N, M) % 2 == 1 else "Malvika"