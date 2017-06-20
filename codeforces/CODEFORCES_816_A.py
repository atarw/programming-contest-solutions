#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

hr, mn = stdin.readline ().split (':')
mn = mn.strip()
cnt = 0

while (hr + mn).strip () != (hr + mn)[::-1].strip():
	mn2 = int (mn) + 1
	cnt += 1
	
	if mn2 >= 60:
		hr2 = (int (hr) + 1) % 24
		hr = str (hr2)
		
		if len (hr) == 1:
			hr = '0' + hr
		
		mn = '00'
	else:
		mn = str (mn2)
		
		if len (mn) == 1:
			mn = '0' + mn
	
print cnt