from sys import stdin

N, M = map (int, stdin.readline ().split ())

parent, rank = [0] * N, [0] * N

for n in xrange (N):
    parent [n] = n

def find (item):
	if parent [item] != item:
		parent [item] = find (parent [item])
		
	return parent [item]

def union (item1, item2):
	root1, root2 = find (item1), find (item2)
	
	if root1 != root2:
		if rank [root1] > rank [root2]:
			parent [root2] = root1
			find (item2)
			
		elif rank [root1] < rank [root2]:
			parent [root1] = root2
			find (item1)
		else:
			parent [root2] = root1
			rank [root1] += 1
			find (item2)

	return root1 != root2

edges = []

for m in xrange (1, M + 1):
	u, v = map (int, stdin.readline ().split ())
	
	if union (u - 1, v - 1):
		edges.append (m)
	
	if len (edges) == N - 1:
		break

if len (edges) == N - 1:
	for edge in edges:
		print edge
else:
	print 'Disconnected Graph'