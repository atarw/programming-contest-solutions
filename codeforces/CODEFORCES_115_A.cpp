#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>

using namespace std;

vector <vector <int> > list;

int dfs (int u) {
	int d = 1;
	
	for (int v : list[u])
		d = max (d, 1 + dfs (v));
	
	return d;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	
	list = vector <vector <int> > (N);
	vector <int> roots;
	
	for (int n = 0; n < N; ++n) {
		int parent;
		cin >> parent;
		
		if (parent == -1)
			roots.push_back (n);
		else
			list[parent - 1].push_back (n);
	}
	
	int groups = 0;
	
	for (int root : roots)
		groups = max (groups, dfs (root));
	
	cout << groups;
}