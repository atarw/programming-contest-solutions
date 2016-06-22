import itertools
import sys

R = int (sys.stdin.readline ())
L = int (sys.stdin.readline ())
amt = 0

matrix = [0] * R
cache = [[0] * (R + 1) for x in xrange (1 << L)]

for r in xrange (R):
    s = sys.stdin.readline ().split ()

    for l in xrange (L):
        matrix [r] ^= ((-int (s [l])) << l) & (1 << l)

def ways (row, above):
    if (cache [row][above] == 0):
        cache [row][above] = 1

        if (row == R):
            amt = amt + 1
        else:
            ways (row + 1, matrix [row] ^ above);
            ways (row + 1, matrix [row]);

ways (1, matrix [0])
print (amt)
