from sys import stdin

def query (bit, pos):
	ret = 0
	
	while pos > 0:
		ret += bit [pos]
		pos -= pos & -pos
		
	return ret

def update (bit, pos, inc):
	while pos < len (bit):
		bit [pos] += inc
		pos += pos & -pos

N, M = map (int, stdin.readline ().split ())
sum_bit, freq_bit = [0] * (N + 1), [0] * 100001

arr = map (int, stdin.readline ().split ())

for n in xrange (N):
	update (sum_bit, n + 1, arr [n]), update (freq_bit, arr [n], 1)

for m in xrange (M):
	cmd = stdin.readline ().split ()
	
	if cmd [0] == 'C':
		x, v = int (cmd [1]), int (cmd [2])
		update (sum_bit, x, v - arr [x - 1]), update (freq_bit, arr [x - 1], -1), update (freq_bit, v, 1)
		arr [x - 1] = v
		
	elif cmd [0] == 'S':
		l, r = int (cmd [1]) - 1, int (cmd [2])
		print query (sum_bit, r) - query (sum_bit, l)
		
	else:
		v = int (cmd [1])
		print query (freq_bit, v)