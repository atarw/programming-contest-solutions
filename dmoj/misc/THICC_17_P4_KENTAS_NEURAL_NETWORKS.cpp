#include <iostream>
#include <algorithm>
#include <string.h>

using namespace std;

int dp [101][101];
string s1, s2;

inline int minn (int a, int b) {
	return a > b ? b : a;
}

inline int solve (int n, int m) {
	if (dp[n][m] != -1)
		return dp[n][m];
		
	if (s1.size () == n)
		return dp[n][m] = s2.size () - m;
	
	if (s2.size () == m)
		return dp[n][m] = s1.size () - n;
			
	if (s1[n] == s2[m])
		return dp[n][m] = solve (n + 1, m + 1);
	else
		return dp[n][m] = 1 + minn (minn (solve(n + 1, m), solve(n, m + 1)), solve (n + 1, m + 1));
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	cout.precision (7);
	
	cin >> s1;

	int N;
	cin >> N;
	
	double best = 1e10;
	
	for (int n = 0; n < N; ++n) {
		int t;
		cin >> t;
		double avr = 0.0;
		
		for (int tt = 0; tt < t; ++tt) {
			cin >> s2;

			if (s1 == s2)
				continue;
			
			memset(dp, -1, sizeof(dp));
			avr += solve(0, 0);
		}
		
		best = min (best, avr / t);
	}
	
	cout << best << endl;
}