#!/usr/bin/python
from __future__ import division

from sys import stdin


def go (N):
	threshold = N * (N - 1) // 2
	low, high, mid = 1, N, -1

	while low < high:
		mid = (low + high) // 2

		cnt = N * mid - (mid * (mid + 1) // 2)

		if 2 * cnt >= threshold:
			high = mid
		else:
			low = mid + 1

	return low


print go (int (stdin.readline ()))
