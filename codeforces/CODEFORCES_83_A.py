from sys import stdin

N = int (stdin.readline ())
arr = list (map (int, stdin.readline ().split ()))

arr2 = []
curr = 1

for n in range (1, N):
	if arr [n] == arr [n - 1]:
		curr += 1
	else:
		arr2.append (curr)
		curr = 1
else:
	arr2.append (curr)

count = 0

for e in arr2:
	count += e * (e + 1) // 2

print (count)