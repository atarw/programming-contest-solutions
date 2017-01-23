#include <stdio.h>
#include <algorithm>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define min(a,b) ((a) < (b) ? (a) : (b))
char _;

/*
 TEST COMMENT
 */

using namespace std;

long long int cache [3000];
int pts [2][3000];//0 = x, 1 = y
bool vis [3000];

int N;

inline long long int dist (int a, int b) {
	long long int diff1 = pts [0][a] - pts [0][b], diff2 = pts [1][a] - pts [1][b];
	return diff1 * diff1 + diff2 * diff2;
}

inline void traverse (int S) {
	cache [S] = 0;
	
	int min;
	
	for (int i = 0; i < N - 1; i++) {
		min = -1;
		
		for (int x = 0; x < N; x++) {
			if (!vis [x] && (min == -1 || cache [x] < cache [min])) {
				min = x;
			}
		}
		
		vis [min] = true;
		
		for (int x = 0; x < N; x++) {
			cache [x] = min (cache [x], cache [min] + dist (min, x));
		}
	}
	
	sort (cache, cache + N);
}

inline int binary (long long int H) {//faster than lowerbound
	int low = 0, mid = N / 2, high = N - 1;
	
	while (low <= high) {
		if (cache [mid] <= H) {
			low = mid + 1;
		}
		else if (cache [mid] > H) {
			high = mid - 1;
		}
		
		mid = (low + high) / 2;
	}
	
	return mid + 1;
}

int main () {
	int X, Q;
	long long int query;
	scan (N);
	
	for (int n = 0; n < N; n++) {
		for (int i = 0; i < 2; i++) {
			scan (pts [i][n]);
		}
		cache [n] = 100000000000;
		vis [n] = false;
	}
	
	scan (X);
	traverse (X - 1);
	
	scan (Q);
	
	while (Q--) {
		scan (query);
		printf("%d\n", binary (query));
	}
}