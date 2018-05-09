#include <iostream>
#include <algorithm>

using namespace std;

inline int max (int a, int b) {
	return a > b ? a : b;
}

int main () {
	cin.sync_with_stdio ();
	cin.tie (0);

	int N;
	cin >> N;

	// both hungry, one hungry, none hungry
	int A[3001], B[3001], C[3001];

	for (int n = 0; n < N; ++n)
		cin >> A[n];

	for (int n = 0; n < N; ++n)
		cin >> B[n];

	for (int n = 0; n < N; ++n)
		cin >> C[n];
	
	// current, fed previous
	int dp[3001][2];
	dp[N - 1][0] = A[N - 1];
	dp[N - 1][1] = B[N - 1];

	for (int n = N - 2; n >= 0; --n) {
		// don't feed or feed next one before feeding current
		dp[n][0] = max (A[n] + dp[n + 1][1], B[n] + dp[n + 1][0]);
		dp[n][1] = max (B[n] + dp[n + 1][1], C[n] + dp[n + 1][0]);
	}

	cout << dp[0][0];
}