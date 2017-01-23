#include <stdio.h>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)

char _;

using namespace std;

inline int min (int a, int b) {
	return a > b ? b : a;
}

int main () {
	int N, K;
	scan (N); scan (K);
	
	int dp [N + 1][K + 1];
	
	for (int n = 0; n <= N; ++n)
		for (int k = 0; k <= K; ++k)
			dp [n][k] = k == 1 || n == k;
	
	for (int n = 1; n <= N; ++n)
		for (int k = 1; k < min (n, K + 1); ++k)
			dp [n][k] = dp [n - 1][k - 1] + dp [n - k][k];
	
	printf ("%d", dp [N][K]);
}