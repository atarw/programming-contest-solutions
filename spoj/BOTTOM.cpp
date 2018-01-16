#include <iostream>
#include <vector>
#include <stack>
#include <unordered_set>
#include <string.h>
#include <algorithm>

using namespace std;

// compress graph into scc dag
// find nodes with outdegree of 0
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
	
	while (true) {
		cin >> N;
		
		if (N == 0)
			break;
		
		cin >> M;
		
		list = vector <vector <int> > (N);
		rev = vector <vector <int> > (N);
		memset (vis, false, sizeof (vis));
		memset (outd, 0, sizeof (vis));
		
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
		
		for (int n = 0; n < N; ++n)
			for (int v = 0; v < list[n].size (); ++v)
				if (id[n] != id[list[n][v]])
					++outd[id[n]];
		
		unordered_set <int> last_cmps;
		
		for (int i = 0; i <= c; ++i)
			if (outd[i] == 0)
				last_cmps.insert (i);
		
		vector <int> ans;
		
		for (int n = 0; n < N; ++n)
			if (last_cmps.count (id[n]))
				ans.push_back (n + 1);
				
		sort (ans.begin (), ans.end ());
		
		for (int i : ans)
			cout << i << " ";
		
		cout << '\n';
	}
}