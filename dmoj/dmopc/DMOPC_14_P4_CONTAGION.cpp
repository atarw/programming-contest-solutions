#include <stdio.h>
#include <string.h>
#include <vector>
#include <algorithm>
#include <limits.h>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define min(a,b) ((a) < (b) ? (a) : (b))
char _;

using namespace std;

vector <long long int> cache;
vector <int> x, y;
vector <bool> vis;

long long int dist (int a, int b) {
		long long int diff1 = x [a] - x [b], diff2 = y [a] - y [b];
		return diff1 * diff1 + diff2 * diff2;
}

void traverse (int S) {
	cache [S] = 0;
	
	int min = -1;
	
	for (int i = 0; i < cache.size () - 1; i++) {
		min = -1;
		
		for (int x = 0; x < cache.size (); x++) {
			if (!vis [x] && (min == -1 || cache [x] < cache [min])) {
				min = x;
			}
		}
		
		vis [min] = true;
		
		for (int x = 0; x < cache.size (); x++) {
			cache [x] = min (cache [x], cache [min] + dist (min, x));
		}
	}
	
	sort (cache.begin (), cache.end ());
}

int binary (long long int H) {
	int low = 0, mid = cache.size () / 2, high = cache.size () - 1;
	
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
	int N, X, Q;
	long long int query;
	scan (N);
	
	x = vector <int> (N);
	y = vector <int> (N);
	cache = vector <long long int> (N);
	vis = vector <bool> (N);
	
	for (int n = 0; n < N; n++) {
		scan (x [n]); scan (y [n]);
		cache [n] = 100000000000;
		vis [n] = false;
	}
	
	scan (X);
	traverse (X - 1);
	
	scan (Q);
	
	for (int q = 0; q < Q; q++) {
		scanf ("%lld", &query);
		printf ("%d\n", binary (query));
	}
}