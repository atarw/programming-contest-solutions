import sys

ln = sys.stdin.readline ()
stretch = 1
last = ln [0]

for i in xrange (1, len (ln)):
    if ln [i] == last:
        stretch = stretch + 1
    else:
        stretch = 1

    if stretch >= 7:
        break

    last = ln [i]

print ("YES" if stretch >= 7 else "NO")
