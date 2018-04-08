#include <iostream>
#include <algorithm>
#include <vector>
#include <string.h>

using namespace std;

int N;
int black[4][101][101];

int settl (int t, int c) {
	// set black[t]'s top left to c
	int ans = 0;
	
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < N; ++j) {
			if ((i + j) % 2 == 0) {
				// should be c
				if (black[t][i][j] != c)
					++ans;
			}
			else {
				// should be opposite of c
				if (black[t][i][j] == c)
					++ans;
			}
		}
	}
	
	return ans;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	cin >> N;
	
	for (int i = 0; i < 4; ++i) {
		for (int n = 0; n < N; ++n) {
			for (int n2 = 0; n2 < N; ++n2) {
				char inp;
				cin >> inp;
				
				black[i][n][n2] = inp == '1';
			}
		}
	}
	
	vector<int> p = vector<int>(4);
	
	for (int i = 0; i < 4; ++i)
		p[i] = i;
	
	int best = 1 << 25;
		
	while (next_permutation (p.begin (), p.end ())) {		
		int ans = settl (p[0], 1) + settl (p[1], 0) + settl (p[2], 0) + settl (p[3], 1); // set top left to black
		int ans2 = settl (p[0], 0) + settl (p[1], 1) + settl (p[2], 1) + settl (p[3], 0); // set top left to white
		best = min (best, min (ans, ans2));
	}
	
	cout << best;
}