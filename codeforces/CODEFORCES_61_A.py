#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

ln = stdin.readline ().strip ('\n')
ln2 = stdin.readline ().strip ('\n')

for i, j in zip (ln, ln2):
	stdout.write ('0' if i == j else '1')