#include <stdio.h>
#include <string.h>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define min(a,b) ((a) > (b) ? (b) : (a))
#define INF 0x3f3f3f3f

char _;

using namespace std;

int N;
int days [100];
int cache [101][2][2];//day + gym + contest -> min days rested so far

int solve (int n, int g, int c) {
	if (cache [n][g][c] != INF) {
		return cache [n][g][c];
	}
	
	if (n == N) {
		return cache [n][g][c] = 0;
	}
	
	cache [n][g][c] = 1 + solve (n + 1, 0, 0);//default
	
	if (!g && (days [n] == 2 || days [n] == 3)) {//do gym
		cache [n][g][c] = min (cache [n][g][c], solve (n + 1, 1, 0));
	}
	
	if (!c && (days [n] == 1 || days [n] == 3)) {//do contest
		cache [n][g][c] = min (cache [n][g][c], solve (n + 1, 0, 1));
	}
	
	return cache [n][g][c];
}

int main () {
	scan (N);
	
	for (int n = 0; n < N; ++n) {
		scan (days [n]);
	}
	
	memset (cache, INF, sizeof (cache));
	
	printf ("%d", solve (0, 0, 0));
}