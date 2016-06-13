#include <stdio.h>
#include <string.h>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a)>(b)?(a):(b))
char _;

using namespace std;

int arr [201];
int cache [201][201];

int best (int l, int r) {
	if (!cache [l][r] && l != r) {
		for (int i = l + 1; i < r; i++) {
			cache [l][r] = max (cache [l][r], arr [l] + arr [i] + arr [r] + best (l, i) + best (i, r));
		}
	}
	
	return cache [l][r];
}

int main () {
	int N;
	
	while (true) {
		scan (N);
		
		if (!N) {
			break;
		}
		
		memset (cache, 0, sizeof (cache));
		
		for (int n = 0; n < N; n++) {
			scan (arr [n]);
		}
		
		printf ("%d\n", best (0, N - 1));
	}
}