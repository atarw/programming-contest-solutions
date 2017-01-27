#include <stdio.h>
#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)

int main () {
	int N;
	char _;
	
	for (int t = 0; t < 10; ++t) {
		scan (N);
		printf ("%d", N * 2 - 1);
		putchar_unlocked ('\n');
		scan (N);
	}
}