from sys import stdin

def lcp (s1, s2):
	for n in xrange (min (len (s1), len (s2))):
		if s1 [n] != s2 [n]:
			return n
			
	return min (len (s1), len (s2))

T = int (stdin.readline ())

for t in xrange (T):
	ln = stdin.readline ().strip ('\n')
	list = sorted ([ln [n:] for n in xrange (len (ln))]) # get suffixes of string, sort
	ans = len (list [0])

	for n in xrange (1, len (list)):
		ans += len (list [n]) - lcp (list [n - 1], list [n])
		
	print ans + 1 # + 1 is for empty string