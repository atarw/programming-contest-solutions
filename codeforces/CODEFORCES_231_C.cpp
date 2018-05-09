#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<long long int> arr, psa;

int main () {
	cin.sync_with_stdio ();
	cin.tie (0);

	int N;
	long long int K;
	cin >> N >> K;

	arr = vector<long long int> (N);
	psa = vector<long long int> (N + 1);

	for (int n = 0; n < N; ++n)
		cin >> arr[n];

	sort (arr.begin (), arr.end ());

	for (int n = 1; n <= N; ++n)
		psa[n] = psa[n - 1] + arr[n - 1];
	
	int best = -1, bestocc = -1;

	for (int n = 0; n < N; ++n) {
		// make elements arr[n]
		// to make elements from [i, n - 1] equal to arr[n], it will take arr[n] * (n - i) - (psa[n] - psa[i])

		// find i
		int lo = 0, hi = n + 1;

		while (lo < hi) {
			int mid = (lo + hi) / 2;

			if (K >= arr[n] * (n - mid) - (psa[n] - psa[mid]))
				hi = mid;
			else
				lo = mid + 1;
		}

		int i = lo - 1;

		if (i == n)
			continue;
		
		if (n - i > bestocc) {
			bestocc = n - i;
			best = arr[n];
		}
	}

	cout << bestocc << " " << best;
}