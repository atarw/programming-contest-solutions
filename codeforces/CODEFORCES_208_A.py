#!/usr/bin/python
from __future__ import division
from sys import stdin, stdout, maxint
import fractions, math, bisect, string

words = filter (lambda a: a != '', stdin.readline ().strip ('\r\n').split ('WUB'))
print string.join (words, ' ')