from sys import stdin

K, N, M = map (int, stdin.readline ().split ())
list = [[] for n in xrange (N)]

for m in xrange (M):
	a, b, t, h = map (int, stdin.readline ().split ())
	list [a - 1].append ((b - 1, t, h)), list [b - 1].append ((a - 1, t, h))

A, B = map (int, stdin.readline ().split ())

cache = [[1 << 30 if n != A - 1 else 0 for k in xrange (K)] for n in xrange (N)]
q = [(A - 1, 0)]

best = 1 << 30

while q:
	u, k = q.pop ()
	
	if u == B - 1:
		best = min (best, cache [u][k])
	
	for v, t, h in list [u]:
		if k + h < K and cache [v][k + h] > cache [u][k] + t:
			cache [v][k + h] = cache [u][k] + t
			
			if best > cache [v][k + h]:
				q.append ((v, k + h))

print -1 if best == 1 << 30 else best