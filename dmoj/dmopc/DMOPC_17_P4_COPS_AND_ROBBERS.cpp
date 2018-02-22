#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <unordered_map>
#include <stack>

using namespace std;

struct thing {
	int frq, v;

	bool operator< (const thing & p)const {
		return frq < p.frq;
}};

vector<int> arr;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	
	arr = vector<int> (N);
	
	for (int n = 0; n < N; ++n)
		cin >> arr[n];
	
	unordered_map <int, int> frq;
	frq.reserve(N + 1);
	
	for (int n = 0; n < N; ++n)
		++frq[arr[n]];
	
	if (frq.size () == 1) {
		cout << -1;
	}
	else {
		stack <int> anytime;
		priority_queue <thing> pq, pq2;
		
		for (int n = 1; n <= N; ++n)
			if (!frq.count(n))
				anytime.push(n);

		for (const auto &p : frq) {
			thing t = {p.second, p.first};
			pq.push (t);
		}
				
		for (int n = 0; n < N; ++n) {
			bool done = false;
			
			while (!pq.empty ()) {
				thing aa = pq.top ();
				if (aa.v == arr[n]) {
					pq2.push(aa);
					pq.pop();
				}
				else {
					cout << aa.v << " ";
					pq.pop();
					done = true;
					break;				
				}
			}
			
			
			if (!done) {
				while (!pq2.empty ()) {
					thing aa = pq2.top ();
					if (aa.v == arr[n]) {
						pq.push(aa);
						pq2.pop();
					}
					else {
						cout << aa.v << " ";
						pq2.pop();
						done = true;
						break;
					}
				}
			}
			
			if (!done) {
				cout << anytime.top () << " ";
				anytime.pop ();
			}
		}
	}
}