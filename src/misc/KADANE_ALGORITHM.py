from sys import stdin

N = int (stdin.readline ())
arr = map (int, stdin.readline ().split ())

'''
finds the sum of the subarray with the greatest sum in an array with at least one positive number (or empty) - if array has all negative elements, will return 0
for each element, determine whether it is better to scrap the existing subarray and start the new sub array at 'element', or merely add 'element' to the existing subarray
'''

def kadane_algorithm:
	curr, best = 0, 0
	
	for element in arr:
		curr = max (curr + element, element)
		best = max (best, curr)
	
	return best

'''
finds the sum of the subarray with the greatest sum in an array. if elements are all negative, will return the 'least' subarray rather than just 0
same as above algorithm, except curr and best are initialized to the first element so that they have to pick a non empty subarray, and loop iterates through all elements but the first
'''

def kadane_algorithm_improved:
	curr = best = arr [0]
	
	for element in arr [1:]:
		curr = max (element, curr + element)
		best = max (best, curr)
	
	return best