#include <stdio.h>
#include <string.h>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

int cache [1048576]; //1 << 20
unsigned int N, S, L, R, I, N_MINUS_ONE, num, MASK;

inline void ways (unsigned int marbles, int M) {
	if (cache [marbles] < M) {
		if (cache [marbles] == -1) {
			++num;
		}
		cache [marbles] = M;
		
		if (M >= S) {
			for (int n = 0; n < N; ++n) {
				ways (marbles ^ (1 << n), M - S);
			}
		}
		
		if (M >= R) {
			ways (((marbles >> 1) | (marbles << N_MINUS_ONE)) & MASK, M - R);
		}
		
		if (M >= L) {
			ways (((marbles << 1) | (marbles >> N_MINUS_ONE)) & MASK, M - L);
		}
		
		if (M >= I) {
			ways (~marbles & MASK, M - I);
		}
	}
}

int main () {
	int M;
	scan (N); scan (M);
	
	MASK = (1 << N) - 1;
	N_MINUS_ONE = N - 1;//what is this optimization
	
	scan (S); scan (L); scan (R); scan (I);
	
	memset (cache, -1, sizeof (cache));
	
	unsigned int start = 0;
	
	for (int n = 0; n < N; ++n) {
		if (getchar () == 'G') {
			start ^= (1 << (N_MINUS_ONE - n));
		}
	}
	
	ways (start, M);
	
	printf ("%d", num);
}