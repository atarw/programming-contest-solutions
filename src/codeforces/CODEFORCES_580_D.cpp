#include <iostream>
#include <string.h>

#define max(a,b) ((a) < (b) ? (b) : (a))

using namespace std;

int N, M, K;
long long int dish [18];
long long int matrix [18][18];
long long int dp [18][1 << 19];

int check_m (int i) {
	i = i - ((i >> 1) & 0x55555555);
	i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
	return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
}

long long int solve (int curr, int vis) {
	if (dp [curr][vis] != -1)
		return dp [curr][vis];
	
	if (check_m (vis) == M)
		return dp [curr][vis] = 0;
	
	for (int n = 0; n < N; ++n) {
		if (!((vis >> n) & 1)) {
			dp [curr][vis] = max (dp [curr][vis], dish [n] + matrix [curr][n] + solve (n, vis | (1 << n)));
		}
	}
	
	return dp [curr][vis];
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N >> M >> K;
			
	memset (dish, -1L, sizeof (dish));
	memset (matrix, 0L, sizeof (matrix));
	memset (dp, -1L, sizeof (dp));
	
	for (int n = 0; n < N; ++n)
		cin >> dish [n];
	
	int x, y;

	for (int k = 0; k < K; ++k) {
		cin >> x >> y;
		cin >> matrix [x - 1][y - 1];
	}
	
	long long int max = 0;
	
	for (int n = 0; n < N; ++n)
		max = max (max, dish [n] + solve (n, 1 << n));
	
	cout << max;
}