import sys

def seq (ln, ln2):
	if len (ln) > len (ln2) or (len (ln) == len (ln2) and ln != ln2):
		return False
	
	pos = 0
	
	for i in ln2:
		if ln [pos] == i:
			pos += 1
			
		if pos == len (ln):
			return True
	
	return False

print ("YES" if seq ("hello", sys.stdin.readline ()) else "NO")