from sys import stdin
from sys import stdout

print ("YES" if stdin.readline () [-2::-1] == stdin.readline ().strip ("\n") else "NO")