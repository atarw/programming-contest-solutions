#include <stdio.h>
#include <iostream>
#include <string.h>
#include <vector>
#include <queue>

#define max(a,b) ((a) < (b) ? (b) : (a))

using namespace std;

vector <vector <int> > list;
int dist [100001], N;
bool vis [100001], pho [100001], used [100001];

bool dfs (int u) {// trim
	vis [u] = true;
	used [u] |= pho [u];
	
	for (int v : list [u])
		if (!vis [v])
			used [u] |= dfs (v);
		
	vis [u] = false;
	return used [u];
}

pair <int, int> bfs (int s) {
	for (int n = 0; n < N; ++n)
		dist [n] = 1 << 25;
	dist [s] = 0;
	
	queue <int> q;
	q.push (s);
	
	int u = s;
	
	while (!q.empty ()) {
		u = q.front (); q.pop ();
		
		for (int v : list [u]) {
			if (used [v] && dist [v] > dist [u] + 1) {
				dist [v] = dist [u] + 1;
				q.push (v);
			}
		}
	}
	
	u = s; int d = dist [s];
	
	for (int n = 0; n < N; ++n) {
		if (d < dist [n] && used [n]) {
			d = dist [n];
			u = n;
		}
	}
	
	return make_pair (u, d);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	int M;
	cin >> N >> M;
	list = vector <vector <int> > (N);
	
	memset (pho, false, sizeof (pho));
	memset (used, false, sizeof (used));
	memset (vis, false, sizeof (vis));
	
	for (int m = 0, v; m < M; ++m) {
		cin >> v;
		pho [v] = true;
	}
	
	for (int n = 0, a, b; n < N - 1; ++n) {
		cin >> a >> b;
		list [a].push_back (b);
		list [b].push_back (a);
	}
	
	int c = -1;
	
	for (int n = 0; n < N && c == -1; ++n)
		if (pho [n])
			c = n;
	
	dfs (c);
	pair <int, int> p = bfs (c);
	p = bfs (p.first);
	
	c = 0;
	
	for (int n = 0; n < N; ++n)
		c += used [n];
	
	cout << p.second + (c - p.second - 1) * 2;
	return 0;
}