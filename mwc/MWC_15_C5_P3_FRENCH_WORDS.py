#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout

stdin.readline ()
print len (set (stdin.readline ().split ()).intersection (set (stdin.readline ().split ())))