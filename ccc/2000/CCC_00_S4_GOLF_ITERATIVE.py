from sys import stdin, maxint

D, N = int (stdin.readline ()), int (stdin.readline ())
arr, dp = [int (stdin.readline ()) for n in xrange (N)], [maxint] * (D + 1)

dp [D] = 0

for d in xrange (D - 1, -1, -1):
	for a in arr:
		if d + a <= D:
			dp [d] = min (dp [d], dp [d + a] + 1)

print ('Roberta wins in {} strokes.'.format (dp [0]) if dp [0] != maxint else 'Roberta acknowledges defeat.')