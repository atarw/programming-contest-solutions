#include <stdio.h>
#include <iostream>
#include <string.h>
#include <algorithm>
#include <vector>
#include <queue>
#include <functional>

using namespace std;

int N, D;
int arr [1000005];
int dp [1000005];

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N >> D;
	
	for (int n = 0; n < N; ++n)
		cin >> arr [n];
	
	memset (dp, 0x3f3f3f3f, sizeof (dp));
	dp [0] = arr [0];
	
	priority_queue <pair <int, int>, vector <pair <int, int> >, greater <pair <int, int> > > pq;
	pq.push (make_pair (arr [0], 0));
	
	pair <int, int> curr;
	
	while (!pq.empty ()) {
		curr = pq.top (); pq.pop ();

		if (curr.second == N - 1)
			break;
		
		for (int d = 1; d <= D && curr.second + d < N; ++d) {
			if (dp [curr.second + d] > dp [curr.second] + arr [curr.second + d]) {
				dp [curr.second + d] = dp [curr.second] + arr [curr.second + d];
				pq.push (make_pair (dp [curr.second + d], curr.second + d));
			}
		}
	}
	
	cout << dp [N - 1];
}