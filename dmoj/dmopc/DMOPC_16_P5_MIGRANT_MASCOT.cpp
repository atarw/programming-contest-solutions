#include <stdio.h>
#include <iostream>
#include <string.h>
#include <vector>
#include <queue>

#define max(a,b) ((a) < (b) ? (b) : (a))
#define INF 0x3f3f3f3f

using namespace std;

vector <vector <pair <int, int> > > list;
int dp [200005];

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	int N, M;
	cin >> N >> M;
	
	list = vector <vector <pair <int, int> > > (N);
	memset (dp, 0, sizeof (dp));
	
	for (int m = 0, u, v, w; m < M; ++m) {
		cin >> u >> v >> w;
		--u; --v;
		
		list [u].push_back (make_pair (v, w));
		list [v].push_back (make_pair (u, w));
	}
	
	queue <pair <int, int> > q;
	q.push (make_pair (0, 1 << 30));
	pair <int, int> u;
	
	while (!q.empty ()) {
		u = q.front (); q.pop ();
		
		for (pair <int, int> e : list [u.first]) {
			if (dp [e.first] < min (u.second, e.second)) {
				dp [e.first] = min (u.second, e.second);
				q.push (make_pair (e.first, min (u.second, e.second)));
			}
		}
	}
	
	cout << 0 << "\n";
	
	for (int n = 1; n < N; ++n)
		cout << dp [n] << "\n";
	
	return 0;
}