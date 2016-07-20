#include <stdio.h>
#include <string.h>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a) < (b) ? (b) : (a))

using namespace std;

char _;
int N;
int arr [1001];
int cache [1001][1001];

int solve (int n, int last) {
	if (cache [n][last] != 0 || n == N) return cache [n][last];
	
	if (arr [n] > last) return cache [n][last] = max (solve (n + 1, last), arr [n] + solve (n + 1, arr [n]));
	return cache [n][last] = solve (n + 1, last);
}

int main () {
	scan (N);
	
	for (int n = 0; n < N; ++n) {
		scan (arr [n]);
	}
	
	memset (cache, 0, sizeof (cache));
	printf ("%d", solve (0, 0));
}