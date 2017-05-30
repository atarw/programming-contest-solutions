#include <iostream>
#include <string.h>
#include <algorithm>
#include <vector>
#include <stack>

using namespace std;

int N, M, Q;
vector <vector <int> > list, rev, cmp;
bool vis[5001];
stack <int> st;
int cnt;
int id[5001], sz[5001];
int dist[5001][5001];

void dfs (int u) {
	vis[u] = true;

	for (int v : list[u])
		if (!vis[v])
			dfs (v);

	st.push (u);
}

void dfs2 (int u) {
	vis[u] = true;
	id[u] = cnt;

	for (int v : rev[u])
		if (!vis[v])
			dfs2 (v);
}

int dfs3 (int u, int v) {
	if (dist[u][v] != -(1 << 25))
		return dist[u][v];

	if (u == v)
		return dist[u][v] = 0;

	vis[u] = true;

	for (int vv : cmp[u])
		if (!vis[vv])
			dist[u][v] = max (dist[u][v], sz[u] + dfs3 (vv, v));

	vis[u] = false;
	return dist[u][v];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N >> M >> Q;
	
	list = vector <vector <int> > (N);
	rev = vector <vector <int> > (N);

	for (int m = 0, a, b; m < M; ++m) {
		cin >> a >> b;
		--a; --b;
		list[a].push_back (b);
		rev[b].push_back (a);
	}

	// do scc and compress graph
	
	for (int n = 0; n < N; ++n)
		if (!vis[n])
			dfs (n);

	memset (vis, false, sizeof (vis));

	while (!st.empty ()) {
		int u = st.top ();
		st.pop ();

		if (!vis[u]) {
			dfs2 (u);
			++cnt;
		}
	}
	
	cmp = vector <vector <int> > (cnt);

	for (int n = 0; n < N; ++n)
		for (int v : list[n])
			if (id[n] != id[v])
				cmp[id[n]].push_back (id[v]);

	for (int n = 0; n < N; ++n)
		++sz[id[n]];

	// find max distance between every pair of nodes in cmp
	for (int u = 0; u < cnt; ++u)
		for (int v = 0; v < cnt; ++v)
			dist [u][v] = -(1 << 25);
	
	memset (vis, false, sizeof (vis));

	for (int u = 0; u < cnt; ++u)
		for (int v = 0; v < cnt; ++v)
			dfs3 (u, v);

	// answer queries
	for (int q = 0, a, b; q < Q; ++q) {
		cin >> a >> b;
		--a; --b;

		if (id[a] == id[b] || dist[id[a]][id[b]] <= 0 && dist[id[b]][id[a]] <= 0) {
			cout << "Indeterminate\n";
		}
		else {
			if (dist[id[a]][id[b]] <= 0 && dist[id[b]][id[a]] > 0) {
				int tmp = b;
				b = a;
				a = tmp;
			}
			
			cout << a + 1 << " " << dist [id[a]][id[b]] - sz[id[a]] << "\n";
		}
	}
}