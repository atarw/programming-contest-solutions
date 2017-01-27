import itertools
import sys

N = int(sys.stdin.readline ())

list = []

for n in xrange (1, N + 1):
  list.append (n)

for permute in itertools.permutations (list):
  print permute
