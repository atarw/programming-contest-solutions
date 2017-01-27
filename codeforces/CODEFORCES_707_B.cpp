#include <stdio.h>
#include <string.h>
#include <vector>

#define min(a,b) ((a) > (b) ? (b) : (a))

using namespace std;

bool source [100001];
vector <pair <int, pair <int, int> > > edges;

int main () {
	int N, M, K;
	scanf ("%d %d %d", &N, &M, &K);
	edges = vector <pair <int, pair <int, int> > > (M);
	
	if (K == 0) {
		printf ("%d", -1);
		return 0;
	}
	
	for (int m = 0, u, v, l; m < M; ++m) {
		scanf ("%d %d %d", &u, &v, &l); --u; --v;
		edges [m] = make_pair (l, make_pair (u, v));
	}
	
	for (int k = 0, b; k < K; ++k) {
		scanf ("%d", &b); --b;
		source [b] = true;
	}
	
	int min = 0x3f3f3f3f;
	
	for (int m = 0; m < M; ++m) {
		if (source [edges [m].second.first] ^ source [edges [m].second.second]) {
			min = min (min, edges [m].first);
		}
	}
	
	printf ("%d", min == 0x3f3f3f3f ? -1 : min);
}