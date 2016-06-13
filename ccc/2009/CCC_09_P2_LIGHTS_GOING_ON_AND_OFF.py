import itertools
import sys

R = int (sys.stdin.readline ())
L = int (sys.stdin.readline ())
comb = 0
bot = set ()
arr = []

for r in xrange (0, R):
        arr.append ([int (x) for x in sys.stdin.readline ().split ()])

def ways (curr, uplight):
    global comb

    if curr == R - 1:
        hash = ''.join (str (e) for e in uplight)
        if hash not in bot:
            comb = comb + 1
            bot.add (hash)
    else:
        ways (curr + 1, uplight)#no press
        newlist = []

        for x in xrange (0, L):
            newlist.append (arr [curr][x] ^ uplight [x])

        if newlist != uplight:
            ways (curr + 1, newlist)#press

ways (1, arr [1])
print (comb)
