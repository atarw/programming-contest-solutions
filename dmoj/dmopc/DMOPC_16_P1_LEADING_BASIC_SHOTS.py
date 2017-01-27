from sys import stdin

N = int (stdin.readline ())
S = stdin.readline ().strip ('\n')
N2 = int (stdin.readline ())

if S == 'Infront':
	print N - N2
else:
	print N + N2