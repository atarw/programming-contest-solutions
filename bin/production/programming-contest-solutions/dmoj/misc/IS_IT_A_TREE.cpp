#include <stdio.h>
#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

int reached = 0;
bool visited [4];
int matrix [4][4];

void dfs (int u) {
	visited [u] = true;
	reached++;
	
	for (int v = 0; v < 4; v++) {
		if (!visited [v] && matrix [u][v]) {
			dfs (v);
		}
	}
}

int main () {
	int count = 0, s;
	
	for (int i = 0; i < 4; i++) {
		for (int z = 0; z < 4; z++) {
			scan (s);
			count += s;
			matrix [i][z] = s;
		}
	}
	
	if (count == 6) {
		dfs (0);
		
		if (reached == 4) {
			printf ("Yes");
		}
		else {
			printf ("No");
		}
	}
	else {
		printf ("No");
	}
}