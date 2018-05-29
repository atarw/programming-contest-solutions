#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

ln = stdin.readline ().strip ('\n\r')

allLettersSame = True

for i in xrange (1, len (ln)):
	if ln[i] != ln[i - 1]:
		allLettersSame = False

if allLettersSame:
	print 0
else:
	palindrome = True
	
	for i in xrange (0, len (ln) // 2):
		if ln[i] != ln[len (ln) - i - 1]:
			palindrome = False
	
	if palindrome:
		print len (ln) - 1
	else:
		print len (ln)