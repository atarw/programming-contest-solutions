#include <iostream>

using namespace std;

int parent [4001];

int find (int u) {
	if (parent [u] != u)
		parent [u] = find (parent [u]);

	return parent [u];
}

void merge (int u, int v) {
	int r1 = find (u), r2 = find (v);
	parent [r1] = r2;
	find (u); find (v);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int T;
	cin >> T;

	for (int tt = 1; tt <= T; ++tt) {
		int N, M;
		cin >> N >> M;

		for (int n = 0; n < 2 * N; ++n)
			parent [n] = n;

		for (int m = 0, a, b, na, nb; m < M; ++m) {
			cin >> a >> b;
			--a; --b;
			na = a + N; nb = b + N;
			merge (a, nb);
			merge (b, na);
		}

		bool sat = true;

		for (int n = 0; n < N && sat; ++n)
			if (find (n) == find (n + N))
				sat = false;

		cout << "Scenario #" << tt << ":\n" << (sat ? "No suspicious bugs found!" : "Suspicious bugs found!") << "\n";
	}
}