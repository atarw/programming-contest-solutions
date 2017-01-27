from sys import stdin
from string import ascii_lowercase, ascii_uppercase

# get input
essay = stdin.readline ()

# count cases
upper, lower = 0, 0

for letter in essay:
	if letter in ascii_lowercase:
		lower += 1
	elif letter in ascii_uppercase:
		upper += 1

# output essay
if lower != upper:
	print (essay.lower () if lower > upper else essay.upper ())
else:
	print (essay)