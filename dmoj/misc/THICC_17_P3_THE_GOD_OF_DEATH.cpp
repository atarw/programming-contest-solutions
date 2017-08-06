#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector <vector <pair <int, int> > > list;

int upper_bound (int n, int x) {
		int low = 0, high = list [n].size (), mid = -1;

		while (low < high) {
			mid = (low + high) / 2;

			if (list [n][mid].first > x)
				high = mid;
			else
				low = mid + 1;
		}

		return low;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	
	list = vector <vector <pair <int, int> > > (N);
	
	for (int n = 0; n < N; ++n) {
		int l;
		cin >> l;
		list [n].push_back (make_pair (0, l));
	}
	
	int Q, day = 0;
	cin >> Q;
	
	for (int q = 0; q < Q; ++q) {
		char c;
		int x, y;
		cin >> c >> x >> y;
		--x;
		
		if (c == 'C') {
			list [x].push_back (make_pair (++day, y));
		}
		else {
			int idx = upper_bound (x, y) - 1;
			cout << list [x][idx].second << "\n";
		}
	}
}