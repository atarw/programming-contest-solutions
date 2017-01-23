import sys
from itertools import groupby

N = int (sys.stdin.readline ())
ln = sys.stdin.readline ()

grps = 0
last = ln [0]

for i in xrange (1, len (ln)):
	if last != ln [i]:
		grps += 1
		
	last = ln [i]

print (N - grps)