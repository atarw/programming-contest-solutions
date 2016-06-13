import itertools
import sys

N = int(sys.stdin.readline ())

list = []

for n in xrange (1, N + 1):
  list.append (n)

for e1, e2, e3, e4 in itertools.permutations (list):
  print (str (e1) + " " + str (e2) + " " + str (e3) + " " + str (e4))
