//ITERATIVE

#include <stdio.h>
#include <string.h>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a) < (b) ? (b) : (a))

char _;

int main () {
    int cards [200];
    int cache [200][200];
    
	int N; scan (N);
	
	while (N) {
		memset (cache, 0, sizeof (cache));
		
		for (int n = 0; n < N; ++n) {
			scan (cards [n]);
		}
		
		//base case, not needed as memset sets cache to 0
		//for (int be = 0; be + 1 < N; ++be) {
		//	cache [be][be + 1] = 0;
		//}
		
		//invariant -> if cache [be][en] depends on cache [be2][en2], en - be > en2 - be2
		int en;
		
		for (int dist = 2; dist < N; ++dist) {
			for (int be = 0; be + dist < N; ++be) {
				en = be + dist;
				
				for (int chose = be + 1; chose < en; ++chose) {
					cache [be][en] = max (cache [be][en], cards [be] + cards [chose] + cards [en] + cache [be][chose] + cache [chose][en]);
				}
			}
		}
		
		printf ("%d\n", cache [0][N - 1]);
		scan (N);
	}
}