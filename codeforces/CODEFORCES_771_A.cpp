#include <iostream>
#include <algorithm>

using namespace std;

long long parent [150005], sz [150005], edges [150005];

int find (int u) {
	if (u != parent [u])
		return parent [u] = find (parent [u]);
	
	return parent [u];
}

void merge (int u, int v) {
	int r1 = find (u), r2 = find (v);
	
	if (r1 != r2) {
		parent [r1] = r2;
		sz [r2] += sz [r1];
		edges [r2] += edges [r1];
	}
	
	++edges [r2];
	find (u), find (v);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, M;
	cin >> N >> M;
	
	for (int n = 0; n < N; ++n) {
		parent [n] = n;
		sz [n] = 1L;
		edges [n] = 0L;
	}
	
	for (int m = 0, u, v; m < M; ++m) {
		cin >> u >> v;
		merge (u - 1, v - 1);
	}
	
	bool valid = true;
	
	for (int n = 0; n < N; ++n) {
		if ((sz [find (n)] * (sz [find (n)] - 1L)) / 2L != edges [find (n)]) {
			valid = false;
			break;
		}
	}
	
	cout << (valid ? "YES" : "NO");
}