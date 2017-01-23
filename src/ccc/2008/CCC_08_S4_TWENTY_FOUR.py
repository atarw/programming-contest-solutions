import itertools
import sys

N = int (sys.stdin.readline ())
curr = 0

def form1 (c1, o1, c2, o2, c3, o3, c4): #A ~ B ~ C ~ D
	if o1 == '/' and (c2 == 0 or c1 % c2 != 0):
		return -1

	if o2 == '/' and (c3 == 0 or c2 % c3 != 0):
		return -1

	if o3 == '/' and (c4 == 0 or c3 % c4 != 0):
		return -1

	return eval (str (c1) + o1 + str (c2) + o2 + str (c3) + o3 + str (c4))

def form2 (c1, o1, c2, o2, c3, o3, c4): #(A ~ B) ~ C ~ D AND ((A ~ B) ~ C) ~ D
	if o1 == '/' and (c2 == 0 or c1 % c2 != 0):
		return -1

	curr = eval (str (c1) + o1 + str (c2))

	if o2 == '/' and (c3 == 0 or curr % c3 != 0):
		return -1

	if o3 == '/' and (c4 == 0 or c3 % c4 != 0):
		return -1

	return eval (str (curr) + o2 + str (c3) + o3 + str (c4))

def form3 (c1, o1, c2, o2, c3, o3, c4): #(A ~ B) ~ (C ~ D)
	if o1 == '/' and (c2 == 0 or c1 % c2 != 0):
		return -1

	curr = eval (str (c1) + o1 + str (c2))

	if o3 == '/' and (c4 == 0 or c3 % c4 != 0):
		return -1

	curr2 = eval (str (c3) + o3 + str (c4))

	if o2 == '/' and (curr2 == 0 or curr % curr2 != 0):
		return -1

	return eval (str (curr) + o2 + str (curr2))

def form4 (c1, o1, c2, o2, c3, o3, c4): #(A ~ B ~ C) ~ D
	if o1 == '/' and (c2 == 0 or c1 % c2 != 0):
		return -1

	if o2 == '/' and (c3 == 0 or c2 % c3 != 0):
		return -1

	curr = eval (str (c1) + o1 + str (c2) + o2 + str (c3))

	if o3 == '/' and (c4 == 0 or curr % c4 != 0):
		return -1

	return eval (str (curr) + o3 + str (c4))

func_array = [form1, form2, form3, form4]

for i in range (0, N):
	max = 0
	res = 0
	cards = [int (sys.stdin.readline ()), int (sys.stdin.readline ()), int (sys.stdin.readline ()), int (sys.stdin.readline ())]
	cards.sort ()

	for c1, c2, c3, c4 in itertools.permutations (cards, 4):
		if max == 24:
			break

		for o1, o2, o3 in itertools.product ('*+-/', repeat=3):
			for x in range (0, 4):
				res = func_array [x] (c1, o1, c2, o2, c3, o3, c4)

				if res != -1 and res <= 24 and res > max:
					max = res

				if max == 24:
					break

			if max == 24:
				break

	print (int (max))
