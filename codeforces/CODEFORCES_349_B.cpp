#include <iostream>
#include <algorithm>

using namespace std;

inline int min (int a, int b) {
	return a > b ? b : a;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int V;
	cin >> V;

	int arr[9], mn = 1 << 30;

	for (int i = 0; i < 9; ++i) {
		cin >> arr[i];
		mn = min(mn, arr[i]);
	}

	bool printed = false;

	while (V >= mn) {
		int dig = -1, nxt = -1;

		for (int i = 0; i < 9; ++i) {
			if (V >= arr[i]) {
				int maxdigs = (V - arr[i]) / mn;

				if (dig < maxdigs + 1 || dig == maxdigs + 1 && i > nxt) {
					dig = maxdigs + 1;
					nxt = i;
				}
			}
		}

		if (nxt == -1)
			break;
		
		printed = true;
		V -= arr[nxt];
		cout << nxt + 1;
	}

	if (!printed)
		cout << -1;
}