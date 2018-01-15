#include <iostream>
#include <algorithm>

using namespace std;

int parent [100000];
long long int sz[100000];

struct edge {
	int u, v;
	long long int w;
} edges[100000];

int cmp (const edge&a, const edge& b){
	return b.w < a.w;
}

int find (int u) {
	if (parent[u] != u)
		return parent[u] = find (parent[u]);
	
	return parent[u];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, M;
	cin >> N >> M;
	
	for (int n = 0; n < N; ++n) {
		parent[n] = n;
		sz[n] = 1L;
	}
	
	for (int m = 0; m < M; ++m) {
		cin >> edges[m].u >> edges[m].v >> edges[m].w;
		--edges[m].u; --edges[m].v;
	}
	
	sort (edges, edges + M, &cmp);
	
	long long int ans = 0L, sum = 0L, MOD = 1000000000L;
	
	for (int m = 0; m < M; ++m)
		sum += edges[m].w;
	
	for (int m = 0; m < M; ++m) {
		int r1 = find (edges[m].u), r2 = find (edges[m].v);
		
		if (r1 != r2) {
			ans += sz[r1] * sz[r2] * sum;
			ans %= MOD;
			
			parent[r1] = r2;
			sz[r2] += sz[r1];
		}
		
		sum -= edges[m].w;
	}
	
	cout << ans;
}