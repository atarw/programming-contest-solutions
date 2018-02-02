#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int T;
	cin >> T;
	
	vector <int> arr, arr2;
	vector <pair <int, int> > pos;
	
	for (int t = 0, N; t < T; ++t) {
		cin >> N;
		arr = vector <int> (N);
		arr2 = vector <int> (N);
		pos = vector <pair <int, int> > ();
		
		for (int n = 0; n < N; ++n) {
			cin >> arr[n];
			pos.push_back (make_pair (arr[n], n));
		}
		
		sort (pos.begin (), pos.end ());
		
		int j = 0;
		
		for (auto const&i : pos)
			arr2[j++] = i.second;
		
		int a = 0, b = 1, start = 0, best = 0;
		
		while (b < N) {
			while (b < N && arr2[b] > arr2[b - 1])
				++b;
				
			best = max (best, b - a);
			a = b;
			++b;
		}
			
		cout << N - best << endl;
	}
}