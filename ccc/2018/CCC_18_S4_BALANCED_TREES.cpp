#include <iostream>
#include <unordered_map>
#include <math.h>

using namespace std;

unordered_map <int, long long int> dp;

long long int solve (int n) {
	if (dp[n])
		return dp[n];
	
	if (n == 1)
		return dp[n] = 1;
	
	long long int ans = 0L;
	
	// in the naive loop, there are multiple values for k where q = n / k and you recurse for each one
	// here you only recurse once for each one, and multiply the result by the number of k values
	for (int k = 2; k <= n; ++k) {
		int q = n / k;
		int mink = k;
		int maxk = n / q;
		
		ans += solve (q) * (maxk - mink + 1);
		k = maxk;
	}
	
	return dp[n] = ans;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	int N;
	cin >> N;
	cout << solve (N);
}