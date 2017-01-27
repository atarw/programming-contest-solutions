#include <string.h>
#include <stdio.h>
#include <algorithm>

#define max(a,b) ((a) > (b) ? (a) : (b))
#define scan(x) do{while((x=getchar())<'0');for(x-='0';'0'<=(_=getchar());x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

int ln [3000];
int bag [100];
int cache [3001][101][101][2];

int N, M;

int solve (int curr, int s, int e, int take) {
	if (cache [curr][s][e][take] != -1) {
		return cache [curr][s][e][take];    
	}
	
	if (curr == N && s >= e) {
		return cache [curr][s][e][take] = 0;    
	}
	
	if (curr < N) {
		cache [curr][s][e][take] = solve (curr + 1, s, e, 1);
		
		if (take) {
			cache [curr][s][e][take] = max (cache [curr][s][e][take], ln [curr] + solve (curr + 1, s, e, 0));
		}
	}
	
	if (s < e) {
		if (take) {
			cache [curr][s][e][take] = max (cache [curr][s][e][take], bag [e - 1] + solve (curr, s, e - 1, 0));
		}
		else {
			cache [curr][s][e][take] = max (cache [curr][s][e][take], solve (curr, s + 1, e, 1));
		}
	}
	
	return cache [curr][s][e][take];
}

int main() {
	scan (N);
	
	for (int n = 0; n < N; ++n) {
		scan (ln [n]);
	}
	
	scan (M);
	
	for (int m = 0; m < M; ++m) {
		scan (bag [m]);    
	}
	
	sort (bag, bag + M);
	memset (cache, -1, sizeof (cache));
	
	printf ("%d", solve (0, 0, M, 1));
}