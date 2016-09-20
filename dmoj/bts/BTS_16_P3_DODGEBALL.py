from sys import stdin

# get input
N = int (stdin.readline ())
arr = [ln [0].lower () for ln in stdin.readline ().split ()]

arr2 = []
adj = 1

# compress adjacent students
for n in range (1, N):
	if arr [n] == arr [n - 1]:
		adj += 1
	else:
		arr2.append (adj)
		adj = 1
else:
	arr2.append (adj)

count = 0

# get choices
for e in arr2:
	count += e * (e + 1) // 2
	
print (count % 1000000007)