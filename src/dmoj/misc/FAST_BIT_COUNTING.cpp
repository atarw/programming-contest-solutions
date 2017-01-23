//hamming weight wikipedia page is very insightful

int setbits (unsigned long long i) {
	i -= (i >> 1) & 0x5555555555555555;
	i = (i & 0x3333333333333333) + ((i >> 2) & 0x3333333333333333);
	i = (i + (i >> 4)) & 0x0f0f0f0f0f0f0f0f;
	return (i * 0x0101010101010101) >> 56;
}