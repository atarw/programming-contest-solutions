from __future__ import division

from sys import stdin

v_in, r_f, r_g = map (int, stdin.readline ().split ())
print v_in * (1 + r_f / r_g)