#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int T;
	cin >> T;
	
	for (int t = 1; t <= T; ++t) {
		int N;
		cin >> N;
		
		vector<int> half1 = vector<int> (), half2 = vector<int> ();
		
		for (int n = 0, v; n < N; ++n) {
			cin >> v;
			
			if (n % 2)
				half2.push_back (v);
			else
				half1.push_back (v);
		}
		
		sort (half1.begin (), half1.end ());
		sort (half2.begin (), half2.end ());
		
		bool good = true;
		int idx = 1;
		
		for (int i = 0; i < half2.size () && good; ++i) {
			if (half2[i] < half1[i]) {
				good = false;
				--idx;
			}
			else {
				if (i + 1 < half1.size () && half2[i] > half1[i + 1]) {
					good = false;
				}
				else {
					idx += 2;
				}
			}
		}
		
		printf ("Case #%d: ", t);
		
		if (good) {
			printf ("OK\n");
		}
		else {
			printf ("%d\n", idx);
		}
	}
}