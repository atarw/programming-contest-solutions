#include <stdio.h>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

int main () {
	
	int arr [3];
	
	for (int i = 0; i < 3; i++) {
		scan (arr [i]);
	}
	
	printf ("%d\n%d\n%d", (arr [0] - arr [1] + arr [2]) / 2 + 10, (arr [0] - arr [2] + arr [1]) / 2 + 10, (arr [1] - arr [0] + arr [2]) / 2 + 10);
}