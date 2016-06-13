import sys

pre = []
F, R = [int (n) for n in sys.stdin.readline ().split ()]
inp = []
pref = []

for f in xrange (0, F):
    pref = []
    inp = [int (i) for i in sys.stdin.readline ().split ()]

    pref.append (0)
    for i in xrange (0, R):
        pref.append (int (pref [i] + inp [i]))

    pre.append (pref)

Q = int (sys.stdin.readline ())

for q in xrange (0, Q):
    a, b, c = [int (n) for n in sys.stdin.readline ().split ()]
    print (pref [c - 1][b] - pref [c - 1][a - 1])
