#include <iostream>
#include <algorithm>
#include <unordered_set>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, M;
	cin >> N >> M;
	
	unordered_set <int> toys;
	toys.reserve (N);
	
	for (int n = 0, a; n < N; ++n) {
		cin >> a;
		toys.insert (a);
	}
	
	unordered_set <int> buy;
	
	for (int i = 1; i <= 1000000000; ++i) {
		if (M < i)
			break;
		
		if (toys.count (i))
			continue;
		
		buy.insert (i);
		M -= i;
	}
	
	cout << buy.size () << "\n";
	
	for (int i : buy)
		cout << i << " ";
}