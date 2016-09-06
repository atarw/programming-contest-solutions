from sys import stdin

def edge (ln):
	return ord (ln [0]) - 65, ord (ln [1]) - 65

roads = []
parent = []

def find (e):
	if parent [e] != e:
		parent [e] = find (parent [e])
		
	return parent [e]
	
def union (e1, e2):
	root1, root2 = find (e1), find (e2)
	parent [root1] = root2
	find (e1), find (e2)

while True:
	ln = stdin.readline ().strip ('\n')
	
	if ln == '**':
		break
	
	roads.append (ln)

total = 0
disconnect = []

for test in roads:
	parent = [x for x in xrange (26)]
	
	for ln in roads:
		if ln != test:
			union (edge (ln) [0], edge (ln) [1])
	
	if find (0) != find (1):
		total += 1
		disconnect.append (test)
	
for road in disconnect:
	print road

print 'There are ' + str (total) + ' disconnecting roads.'