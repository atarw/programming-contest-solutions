#include <stdio.h>
#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define min(a,b) ((a) < (b) ? (a) : (b))
#define max(a,b) ((a) < (b) ? (b) : (a))
char _;

int main () {
	int N, M;
	scan (N);
	
	int arr1 [N];
	
	for (int n = 0; n < N; n++) {
		scan (arr1 [n]);
	}
	
	scan (M);
	int arr2 [M];
	
	for (int m = 0; m < M; m++) {
		scan (arr2 [m]);
	}
	
	int cache [2][min (N, M) + 1];
	
	for (int n = 0; n < min (N, M) + 1; n++) {
		cache [0][n] = 0;
		cache [1][n] = 0;
	}
	
	int tmp;//stores cache index
	
	for (int i = M; i >= 0; i--) {
		tmp = i&1;//cool trick!
		
		for (int j = N; j >= 0; j--) {
			if (i == M || j == N) {
				cache [tmp][j] = 0;
			}
			else if (arr1 [i - 1] == arr2 [j - 1]) {
				cache [tmp][j] = cache [1 - tmp][j + 1] + 1;
			}
			else {
				cache [tmp][j] = max (cache [1 - tmp][j], cache [tmp][j + 1]);
			}
		}
	}
	
	printf ("%d", cache [0][0]);
}