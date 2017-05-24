#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int N;
vector <vector <int> > list;
int dist [500001], dist2 [500001];
bool vis [500001];

void dfs (int u, int d) {
	vis [u] = true;

	for (int v : list [u]) {
		if (!vis [v]) {
			dist [v] = d + 1;
			dfs (v, d + 1);
		}
	}

	vis [u] = false;
}

int get_furthest () {
	int max_node = 0;

	for (int n = 0; n < N; ++n)
		if (dist[max_node] < dist[n])
			max_node = n;

	return max_node;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N;
	list = vector <vector <int> > (N);

	for (int n = 0, u, v; n < N - 1; ++n) {
		cin >> u >> v;
		--u; --v;
		list [u].push_back (v); list [v].push_back (u);
	}

	dfs (0, 1);

	int s = get_furthest ();
	dfs (s, 1);

	int e = get_furthest ();

	for (int n = 0; n < N; ++n)
		dist2 [n] = dist [n];

	dfs (e, 1);

	for (int n = 0; n < N; ++n)
		cout << max (dist [n], dist2 [n]) << "\n";
}