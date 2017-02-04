#include <stdio.h>
#include <string.h>
#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int N, pre = 0, p [200005], b [200005], low [200005];
bool vis [200005];
stack <int> st;

vector <vector <int> > comp;

inline int min (int a, int b) {
	return a > b ? b : a;
}

void scc (int u) {
	low [u] = pre++;
	vis [u] = true;
	st.push (u);
	
	int _min = low [u];
	
	if (!vis [p [u]])
		scc (p [u]);
	
	_min = min (_min, low [p [u]]);
	
	if (_min < low [u]) {
		low [u] = _min;
		return;
	}
	
	int w;
	vector <int> cc;
	
	do {
		w = st.top ();
		st.pop ();
		
		 cc.push_back (w);
	}
	while (w != u);
	
	comp.push_back (cc);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N;
	
	for (int n = 0; n < N; ++n)
		cin >> p [n];
		
	for (int n = 0; n < N; ++n)
		--p [n];
	
	for (int n = 0; n < N; ++n)
		cin >> b [n];
	
	memset (low, -1, sizeof (low));
	memset (vis, false, sizeof (vis));
	
	for (int n = 0; n < N; ++n)
		if (!vis [n])
			scc (n);
			
	int changes = comp.size () == 1 ? 0 : comp.size ();
	
	int sum = 0;
	
	for (int n = 0; n < N; ++n)
		sum += b [n];
	
	if (sum % 2 == 0)
		++changes;

	cout << changes;
	return 0;
}