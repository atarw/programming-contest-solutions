#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_set>
#include <queue>

using namespace std;

vector<int> arr;
vector<vector<int> > list, buckets;
vector<priority_queue<int> > goods;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, M, K, S;
	cin >> N >> M >> K >> S;
	
	arr = vector<int> (N);
	
	for (int n = 0; n < N; ++n) {
		cin >> arr[n];
		--arr[n];
	}
	
	list = vector<vector<int> > (N);
	
	for (int m = 0, u, v; m < M; ++m) {
		cin >> u >> v;
		--u; --v;
		list[u].push_back (v);
		list[v].push_back (u);
	}
	
	buckets = vector<vector<int> > (K);
	
	for (int n = 0; n < N; ++n)
		buckets[arr[n]].push_back (n);
	
	goods = vector<priority_queue<int> > (N);
	
	for (int k = 0; k < K; ++k) {
		vector<int> dp = vector<int> (N, 1 << 25);
		queue<int> q;
		
		for (int i : buckets[k]) {
			q.push (i);
			dp[i] = 0;
		}
		
		while (!q.empty ()) {
			int u = q.front ();
			q.pop ();
			
			for (int v : list[u]) {
				if (dp[v] > dp[u] + 1) {
					dp[v] = dp[u] + 1;
					q.push (v);
				}
			}
		}
		
		for (int n = 0; n < N; ++n)
			goods[n].push (-dp[n]);
	}
	
	for (int n = 0; n < N; ++n) {
		long total = 0L;
		int chosen = 0;
		
		while (chosen < S) {
			total += -goods[n].top ();
			goods[n].pop ();
			++chosen;
		}
		
		cout << total << " ";
	}
}