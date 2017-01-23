from sys import stdin, stdout, setrecursionlimit

setrecursionlimit (100000)

#input
N, M = map (int, stdin.readline ().split ())
pho, vis, useful = [False] * N, [False] * N, [False] * N
graph = [[] for n in xrange (N)]
root = 0

for p in map (int, stdin.readline ().split ()):
	pho [p], root = True, p

for n in xrange (N - 1):
	a, b = map (int, stdin.readline ().split ())
	graph [a].append (b), graph [b].append (a)

#trim such that all leaves are pho restaurants
def dfs (u):
	vis [u] = True

	if pho [u]:
		useful [u], root = True, u

	for v in graph [u]:
		if not vis [v] and dfs (v):
			useful [u] = True

	vis [u] = False
	return useful [u]

dfs (root)

#find furthest point + diameter
def bfs ():
	q, cache = [root], [None] * N
	cache [root] = 0

	while q:
		curr = q.pop ()
		vis [curr] = False

		for v in graph [curr]:
			if useful [v] and (cache [v] == None or cache [v] > cache [curr] + 1):
				cache [v] = cache [curr] + 1

				if not vis [v]:
					q.append (v)
					vis [v] = True

	pos, val = 0, cache [0]

	for n in xrange (1, N):
		if val < cache [n]:
			pos, val = n, cache [n]

	return pos, val

root = bfs () [0]
diam = bfs () [1]
in_tree = useful.count (True)

print diam + (in_tree - diam - 1) * 2
