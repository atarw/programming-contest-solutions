#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, K;
	cin >> N >> K;
	
	vector <int> arr = vector <int> (N);
	vector <int> cnt = vector <int> (1000001);
	
	for (int n = 0; n < N; ++n)
		cin >> arr[n];
		
	long long ans = 0L;
	int a = 0, b = 0, frq = 0;
	
	while (b < N) {
		while (b < N && frq < K)
			if (++cnt[arr[b++]] == 1)
					++frq;

		while (frq >= K) {
			ans += (N - b + 1L);
			
			if (--cnt[arr[a++]] == 0)
				--frq;
		}
	}
	
	cout << ans;
}
