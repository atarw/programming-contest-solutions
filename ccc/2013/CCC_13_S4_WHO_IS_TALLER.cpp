#include <stdio.h>
#include <iostream>
#include <string.h>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, E;
vector <vector <int> > list;

bool dfs (int u) {
	if (u == E)
		return true;
	
	for (int v : list [u])
		if (dfs (v))
			return true;
	
	return false;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N >> M;
	list = vector <vector <int> > (N);
	
	for (int m = 0, u, v; m < M; ++m) {
		cin >> u >> v; --u; --v;
		list [u].push_back (v);
	}
	
	int p, q;
	cin >> p >> q; --p; --q;
		
	E = q;
	
	if (dfs (p))
		cout << "yes";
	else {
		E = p;
		
		if (dfs (q))
			cout << "no";
		else
			cout << "unknown";
	}
	
	return 0;
}