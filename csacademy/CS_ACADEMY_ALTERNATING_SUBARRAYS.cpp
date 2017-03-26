#include <stdio.h>
#include <iostream>
#include <string.h>
#include <vector>
#include <algorithm>

using namespace std;

int N, arr [100005];

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N;
	
	for (int n = 0; n < N; ++n)
		cin >> arr [n];
	
	int curr = 1, best = 1;
	int dir = 0;
	
	for (int n = 1, n2 = 0; n < N;) {		
		if (dir == 0) {
			if (arr [n] > arr [n2]) {
				dir = -1;
				curr = 1;
				++n; ++n2;
			}
			else if (arr [n] < arr [n2]) {
				dir = 1;
				curr = 1;
				++n; ++n2;
			}
			else {
				curr = 0;
				dir = 0;
				++n; ++n2;
			}
		}
		else if (dir == 1) {//second elem bigger
			if (arr [n] > arr [n2]) {
				++curr;
				dir = -1;
				++n; ++n2;
			}
			else {
				curr = 0;
				dir = 0;
			}
		}
		else if (dir == -1) {//first elem bigger
			if (arr [n] < arr [n2]) {
				++curr;
				dir = 1;
				++n; ++n2;
			}
			else {
				curr = 0;
				dir = 0;
			}
		}
		
		best = max (best, curr + 1);
	}
	
	cout << best;
}