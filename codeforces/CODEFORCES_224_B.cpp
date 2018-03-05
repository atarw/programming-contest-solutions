#include <iostream>
#include <algorithm>
#include <unordered_map>

using namespace std;

int arr[100000];

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, K;
	cin >> N >> K;
	
	for (int n = 0; n < N; ++n)
		cin >> arr[n];
	
	unordered_map<int, int> frq;
	int l = 0, r = 0;
	int bestl = -1, bestr = -1;
	
	while (r < N) {		
		while (r < N && frq.size () < K) {
			++frq[arr[r]];
			++r;
		}
				
		while (l <= r && frq.size () >= K) {
			if (frq.size () == K && (bestl == -1 || bestr - bestl > (r - 1) - l)) {
				bestl = l;
				bestr = r - 1;
			}
			
			--frq[arr[l]];
			
			if (!frq[arr[l]])
				frq.erase(arr[l]);
			
			++l;
		}
	}
	
	if (bestl == -1)
		cout << -1 << " " << -1;
	else
		cout << bestl + 1 << " " << bestr + 1;
}