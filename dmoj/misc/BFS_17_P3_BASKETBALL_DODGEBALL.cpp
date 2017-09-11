#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

bool reach (int x1, int y1, int r1, int x2, int y2) {
	return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= r1 * r1;
}

int dist (vector <vector <int> > g, int s, int e) {
	vector <int> dp = vector <int> (g.size() + 1);
	
	for (int i = 0; i < dp.size(); ++i)
		dp[i] = 1 << 25;
	
	dp[s] = 0;
	
	queue <int> q;
	q.push(s);
	
	while (!q.empty()) {
		int cur = q.front();
		q.pop();
		
		for (int v : g[cur]) {
			if (dp[v] > dp[cur] + 1) {
				dp[v] = dp[cur] + 1;
				q.push(v);
			}
		}
	}
	
	return dp[e];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, M;
	cin >> N >> M;
	vector <int> tx, ty, tr, tx2, ty2, tr2;
	tx = vector <int> (N);
	ty = vector <int> (N);
	tr = vector <int> (N);
	tx2 = vector <int> (M);
	ty2 = vector <int> (M);
	tr2 = vector <int> (M);
	
	vector <vector <int > > g1, g2;
	g1 = vector <vector <int> > (N);
	g2 = vector <vector <int> > (M);
	
	int e1 = -1, e2 = -1;
	int s1 = -1, s2 = -1;
	int m1 = -1, m2 = -1;
	
	for (int n = 0; n < N; ++n) {
		int x, y, r;
		cin >> x >> y >> r;
		tx[n] = x;
		ty[n] = y;
		tr[n] = r;
		
		if (r == 9001)
			e1 = n;
			
		if (y > m1) {
			m1 = y;
			s1 = n;
		}
	}
	
	for (int m = 0; m < M; ++m) {
		int x, y, r;
		cin >> x >> y >> r;
		tx2[m] = x;
		ty2[m] = y;
		tr2[m] = r;
		
		if (r == 9001)
			e2 = m;
			
		if (y > m2) {
			m2 = y;
			s2 = m;
		}
	}
	
	for (int n = 0; n < N; ++n) {
		for (int n2 = 0; n2 < N; ++n2) {
			if (n == n2)
				continue;
			
			if (reach (tx[n], ty[n], tr[n], tx[n2], ty[n2])) {
				g1[n].push_back(n2);
			}
		}
	}
	
	for (int m = 0; m < M; ++m) {
		for (int m2 = 0; m2 < M; ++m2) {
			if (m == m2)
				continue;
			
			if (reach (tx2[m], ty2[m], tr2[m], tx2[m2], ty2[m2])) {
				g2[m].push_back(m2);
			}
		}
	}
	
	int d1 = dist(g1, s1, e1), d2 = dist(g2, s2, e2);
	
	if (d1 > d2)
		cout << ":'(";
	else if (d2 > d1)
		cout << "We are the champions!";
	else
		cout << "SUDDEN DEATH";
}