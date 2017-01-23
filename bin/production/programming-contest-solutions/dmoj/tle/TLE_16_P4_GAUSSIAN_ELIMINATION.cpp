#include <stdio.h>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

int main () {
	int N, M;
	scan (N); scan (M);
	
	if (N == 1 || M == 1)
		puts ("First");
	else
		puts ((N + M) & 1 ? "First" : "Second");
}