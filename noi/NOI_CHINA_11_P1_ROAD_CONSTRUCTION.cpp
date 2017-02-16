#include <stdio.h>
#include <iostream>
#include <string.h>
#include <vector>

using namespace std;

vector <vector <int> > list;
vector <pair <pair <int, int>, int> > edges;
bool vis [1000001];
int size [1000001];

int dfs (int u) {
	vis [u] = true;
	size [u] = 1;
	
	for (int v : list [u])
		if (!vis [v])
			size [u] += dfs (v);
	
	return size [u];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	int N;
	cin >> N;
	
	list = vector <vector <int> > (N);
	edges = vector <pair <pair <int, int>, int> > (N - 1);
	memset (vis, false, sizeof (vis));
	memset (size, 0, sizeof (size));
	
	for (int n = 0, u, v, w; n < N - 1; ++n) {
		cin >> u >> v >> w; --u; --v;
		list [u].push_back (v); list [v].push_back (u);
		edges [n] = make_pair (make_pair (u, v), w);
	}
	
	dfs (0);
	
	long long int cost = 0L;
	
	for (pair <pair <int, int>, int> e : edges) {
		if (size [e.first.first] > size [e.first.second])
			cost += (long long int) e.second * abs (size [e.first.second] * 2 - N);
		else
			cost += (long long int) e.second * abs (size [e.first.first] * 2 - N);
	}
	
	cout << cost;
	return 0;
}