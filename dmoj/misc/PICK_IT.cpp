#include <stdio.h>
#include <string.h>
#include <vector>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a) < (b) ? (b) : (a))

char _;

using namespace std;

int cards [200];
int cache [200][200];

int solve (int be, int en) {
	if (cache [be][en] != -1) {
		return cache [be][en];
	}
	
	if (be + 1 == en) {
		return cache [be][en] = 0;
	}
	
	for (int chose = be + 1; chose < en; ++chose) {
		cache [be][en] = max (cache [be][en], cards [chose] + cards [be] + cards [en] + solve (be, chose) + solve (chose, en));
	}
	
	return cache [be][en];
}

int main () {
	int N; scan (N);
	
	while (N) {
		memset (cache, -1, sizeof (cache));
		
		for (int n = 0; n < N; ++n) {
			scan (cards [n]);
		}
		
		printf ("%d\n", solve (0, N - 1));
		
		scan (N);
	}

	return 0;
}