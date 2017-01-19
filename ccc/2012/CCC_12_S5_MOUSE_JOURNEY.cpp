#include <stdio.h>
#include <string.h>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

int main () {
	int R, C, K;
	scan (R); scan (C); scan (K);
	
	bool cat [25][25];
	int dp [25][25];
	
	memset (cat, false, sizeof (cat));
	memset (dp, 0, sizeof (dp));
	
	for (int k = 0, r, c; k < K; ++k) {
		scan (r); scan (c);
		cat [r - 1][c - 1] = true;
	}
	
	dp [R - 1][C - 1] = 1;
	
	for (int r = R - 2; r >= 0 && !cat [r][C - 1]; --r)
		dp [r][C - 1] = dp [r + 1][C - 1];
	
	for (int c = C - 2; c >= 0 && !cat [R - 1][c]; --c)
		dp [R - 1][c] = dp [R - 1][c + 1];
	
	for (int r = R - 2; r >= 0; --r)
		for (int c = C - 2; c >= 0; --c)
			if (!cat [r][c])
				dp [r][c] += dp [r][c + 1] + dp [r + 1][c];
	
	printf ("%d", dp [0][0]);
}