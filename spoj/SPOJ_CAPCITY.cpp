#include <iostream>
#include <vector>
#include <stack>
#include <string.h>
#include <algorithm>

using namespace std;

// compress graph into scc dag
// find node with outdegree of 0
// if there is only one, then the nodes inside that scc are capital cities, else there are no capital cities
bool vis[100000];
int id[100000], outd[100000];
vector <vector <int> > list, rev;
stack <int> ord;

void dfs (int u) {
	vis[u] = true;
	
	for (int v : list[u])
		if (!vis[v])
			dfs (v);
	
	ord.push (u);
}

void dfs2 (int u, int c) {
	vis[u] = true;
	id[u] = c;
	
	for (int v : rev[u])
		if (!vis[v])
			dfs2 (v, c);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, M;
	cin >> N >> M;
	
	list = vector <vector <int> > (N);
	rev = vector <vector <int> > (N);
	
	for (int m = 0, a, b; m < M; ++m) {
		cin >> a >> b;
		--a; --b;
		list[a].push_back (b);
		rev[b].push_back (a);
	}
		
	for (int n = 0; n < N; ++n)
		if (!vis[n])
			dfs (n);
		
	int c = -1;
	memset (vis, false, sizeof (vis));
	
	while (!ord.empty ()) {
		int nxt = ord.top ();
		ord.pop ();
		
		if (!vis[nxt])
			dfs2 (nxt, ++c);
	}
	
	memset (outd, 0, sizeof (outd));
	
	for (int n = 0; n < N; ++n)
		for (int v = 0; v < list[n].size (); ++v)
			if (id[n] != id[list[n][v]])
				++outd[id[n]];
	
	int last_cmp = -1;
	bool good = true;
	
	for (int i = 0; i <= c && good; ++i) {
		if (outd[i] == 0) {
			if (last_cmp == -1)
				last_cmp = i;
			else
				good = false;
		}
	}
			
	if (!good) {
		cout << 0 << '\n';
	}
	else {
		vector <int> capitals;
			
		for (int n = 0; n < N; ++n)
			if (id[n] == last_cmp)
				capitals.push_back (n + 1);
		
		sort (capitals.begin (), capitals.end ());
		
		cout << capitals.size () << '\n';
		
		for (int u : capitals)
			cout << u << " ";
	}
}