from sys import stdin
from sys import stdout

def possible (needed, rounded, pos):
	if sum (needed) == 0:
		return True
	elif pos == len (rounded):
		return False
	else:
		res = False
		
		if rounded [pos][0] and needed [0]:
			res = max (res, possible ([needed [0] - 1, needed [1], needed [2]], rounded, pos + 1))
		if not res and rounded [pos][1] and needed [1]:
			res = max (res, possible ([needed [0], needed [1] - 1, needed [2]], rounded, pos + 1))
		if not res and rounded [pos][2] and needed [2]:
			res = max (res, possible ([needed [0], needed [1], needed [2] - 1], rounded, pos + 1))
			
		return res

F = int (stdin.readline ())
O, V, N = [0] * F, [0] * F, [0] * F

for f in xrange (F):
	O [f], V [f], N [f] = map (int, stdin.readline ().split ('-'))

M = int (stdin.readline ())

rounded = []
gO, gV, gN = 0, 0, 0

for m in xrange (M):
	stat = [0] * 3
	ln = stdin.readline ()
	
	stat [0], stat [1], stat [2] = 1 if 'O' in ln else 0, 1 if 'V' in ln else 0, 1 if 'N' in ln else 0
	
	if sum (stat) > 1:
		rounded.append (stat)
	else:
		gO, gV, gN = gO + (1 if stat [0] == 1 else 0), gV + (1 if stat [1] == 1 else 0), gN + (1 if stat [2] == 1 else 0)

for f in xrange (F):
	if (O [f] <= gO and V [f] <= gV and N [f] <= gN) or possible ([max (0, O [f] - gO), max (0, V [f] - gV), max (0, N [f] - gN)], rounded, 0):
		print ("DA")
	else:
		print ("NE")