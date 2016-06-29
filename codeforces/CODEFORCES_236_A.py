import sys

print ("CHAT WITH HER!" if (len (set ([i for i in sys.stdin.readline ()])) - 1) % 2 == 0 else "IGNORE HIM!")