#include <iostream>
#include <unordered_map>
#include <math.h>
#include <vector>

using namespace std;

unordered_map<int, long long int> dp;
vector<int> dep; // relevant dp states

long long int solve (int n) {
	dp[1] = 1L;
	dep.push_back (1);
	
	for (int i = 2; i <= n;) {
		int q = n / i;
		
		if (n / q != i) {
			i = n / q;
			continue;
		}
		
		dep.push_back (i);
		int last = dep[0];
		long long ans = 0L;

		for (int k = 1; k < dep.size (); ++k) {
			int q2 = i / dep[k];
			
			if (i / q2 != dep[k])
				continue;
			
			ans += dp.at (q2) * (dep[k] - last);
			last = dep[k];
		}
		
		dp.insert(make_pair (i, ans));
		++i;
	}
	
	return dp[n];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	int N;
	cin >> N;
	cout << solve (N);
}