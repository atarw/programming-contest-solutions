import sys

ln = sys.stdin.readline ()

print ("YES" if (ln.find ('H') != -1 or ln.find ('Q') != -1 or ln.find ('9') != -1) else "NO")