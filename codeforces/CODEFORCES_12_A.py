import sys

ln1 = sys.stdin.readline ().lower ()
ln2 = sys.stdin.readline ().lower ()

print (-1 if ln1 < ln2 else (1 if ln1 > ln2 else 0))