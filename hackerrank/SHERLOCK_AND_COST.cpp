#include <bits/stdc++.h>

using namespace std;

int dp [100001][2];
int B [100000];
int N;

int solve (int n, int b) {
	if (dp [n][b] != -1)
		return dp [n][b];
	
	if (n == N)
		return dp [n][b] = 0;
	
	int last = b ? B [n - 1] : 1;
	dp [n][b] = max (abs (B [n] - last) + solve (n + 1, 1), abs (1 - last) + solve (n + 1, 0));
	
	return dp [n][b];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int T;
	cin >> T;

	for (int tt = 0; tt < T; ++tt) {
		cin >> N;
		
		for (int n = 0; n < N; ++n)
			cin >> B [n];

		memset (dp, -1, sizeof (dp));
		
		int ans = max(solve (1, 0), solve (1, 1));
		cout << ans << "\n";
	}
}