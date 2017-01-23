from sys import stdin
from sys import stdout

T = int (stdin.readline ())

def predicate (x, stalls):
	cows = 1
	
	last = stalls [0]
	for stall in stalls [1:]:
		if stall - last >= x: #min distance of x fulfilled
			cows += 1
			last = stall
	
	return cows

for t in xrange (T):
	N, C = map (int, stdin.readline ().strip ("\n").split ())
	stalls = []

	for n in xrange (N):
		stalls.append (int (stdin.readline ().strip ("\n")))
	
	stalls.sort ()
	
	#predicate -> P (x) = the number of cows that can fit in a barn if the minimum distance is x
	#binary search for highest when P (x) >= C using this predicate

	low, high, mid = 0, stalls [len (stalls) - 1], 0

	while (low < high):
		mid = (low + high + 1) / 2
		c = predicate (mid, stalls)
								
		if c < C: #amount of cows that can be serviced is less, min distance should be decreased
			high = mid - 1
		elif c > C: #amount of cows that can be serviced is more, min distance can be increased
			low = mid + 1
		else: #exact
			low = mid
	
	print high