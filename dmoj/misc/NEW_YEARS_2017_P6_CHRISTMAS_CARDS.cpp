#include <stdio.h>
#include <string.h>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define min(a,b) ((a) > (b) ? (b) : (a))

char _;

int N, c [5001], d [5001];
long long int dp [5001][5001];
long long int INF = 1L << 50;

long long int solve (int curr, int hand) {
	if (hand < curr)
		return INF;
		
	if (hand >= N - 1)
		return 0L;
	
	if (dp [curr][hand] != INF)
		return dp [curr][hand];
	
	return dp [curr][hand] = min (c [curr] + solve (curr + 1, hand + d [curr]), solve (curr + 1, hand));
}

int main () {
	scan (N);
	
	for (int n = 0; n < N; ++n) {
		scan (c [n]); scan (d [n]);
	}
	
	for (int n = 0; n < N; ++n)
		for (int n2 = 0; n2 < N; ++n2)
			dp [n][n2] = INF;
	
	printf ("%lld", solve (0, 0));
}