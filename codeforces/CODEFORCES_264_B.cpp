#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>
#include <unordered_map>
#include <unordered_set>

using namespace std;

vector<int> dp;
vector<unordered_set<int> > factors;
unordered_map<int, vector<int> > shared;

int solve (int n) {
	if (dp[n] != -1)
		return dp[n];
		
	if (n == 0)
		return dp[n] = 1;
	
	dp[n] = 1;
	
	for (auto f : factors[n]) {
		auto nxt = lower_bound (shared[f].begin (), shared[f].end (), n);
		
		if (nxt != shared[f].begin ())
			dp[n] = max (dp[n], 1 + solve (*prev (nxt)));
	}
	
	return dp[n];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	
	factors = vector<unordered_set<int> > (N);
	
	for (int n = 0; n < N; ++n) {
		int cur;
		cin >> cur;
		
		int lim = (int) ceil (sqrt (cur)) + 1;
		
		for (int f = 1; f <= lim; ++f) {
			if (cur % f == 0) {
				factors[n].insert (f);
				factors[n].insert (cur / f);
				
				shared[f].push_back (n);
				shared[cur / f].push_back (n);
			}
		}
		
		factors[n].erase (1);
	}
	
	dp = vector<int> (N, -1);
	
	int best = 0;
	
	for (int n = 0; n < N; ++n)
		best = max (best, solve (n));
	
	cout << best;
}