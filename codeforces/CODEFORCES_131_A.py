import sys

ln = sys.stdin.readline ()

if ln.upper () == ln or (ln [0].lower () == ln [0] and ln [1:].upper () == ln [1:]):
	print (ln.swapcase ())
else:
	print (ln)