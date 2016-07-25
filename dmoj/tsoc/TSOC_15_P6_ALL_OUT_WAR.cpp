#include <stdio.h>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define min(a,b) ((a) > (b) ? (b) : (a))
#define max(a,b) ((a) < (b) ? (b) : (a))
#define INF 0x3f3f3f3f

char _;

using namespace std;

int main () {
	int N, Q, r_min = INF, t_min = INF;
	
	scan (N); scan (Q);
	int arr [N];
	
	for (int n = 0; n < N; ++n) {
		scan (arr [n]);
		t_min = min (t_min, arr [n]);
	}
	
	int a, b, c;
	
	while (Q--) {
		scan (a); scan (b); scan (c);
		--a;
		r_min = INF;
		
		for (int i = a; i < b; ++i) {
			arr [i] = max (0, arr [i] - c);
			r_min = min (r_min, arr [i]);
		}
		
		t_min = min (r_min, t_min);
		
		printf ("%d %d\n", r_min, t_min);
	}

	return 0;
}