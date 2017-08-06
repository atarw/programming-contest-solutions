#include <iostream>
#include <string.h>
#include <algorithm>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	
	int arr [N];
	
	for (int n = 0; n < N; ++n)
		cin >> arr [n];
		
	sort (arr, arr + N);
	
	int V = arr [N - 1] * (N - 2) + 1;
	bool top [V], bot [V];
	
	memset (top, false, sizeof (top));
	memset (bot, false, sizeof (bot));
	top [0] = true;
	
	bool used [51];
	memset (used, false, sizeof (used));
	
	for (int n = N - 2; n >= 0; --n) {
		for (int v = 0; v < V; ++v) {
			if (top [v])
				bot [v] = true;
				
			for (int h = 50; h >= arr [n]; --h) {
				if (used [h]) {
					int w = h - arr [n];
					
					if (v - w >= 0 && top [v - w])
						bot [v] = true;
				}
			}
		}
		
		used [arr [n]] = true;
		
		memcpy (top, bot, sizeof (bot));
		memset (bot, false, sizeof (bot));
	}
	
	for (int v = 0; v < V; ++v)
		if (top [v])
			cout << v << " ";
}