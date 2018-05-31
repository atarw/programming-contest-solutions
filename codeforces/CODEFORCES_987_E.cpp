#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> arr;
unordered_map<int, int> inv;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	
	arr = vector<int> (N);
	
	for (int n = 0; n < N; ++n) {
		cin >> arr[n];
		--arr[n];
		inv[arr[n]] = n;
	}
	
	int cnt = 0;
	
	for (int n = 0; n < N; ++n) {
		if (n != arr[n]) {
			int tmp = arr[n];
			int to = inv[n];
						
			arr[n] = n;
			arr[to] = tmp;
			
			inv[tmp] = to;
			inv[n] = n;
			
			++cnt;
		}
	}
	
	if (((3 * N) - cnt) % 2 == 0)
		cout << "Petr";
	else
		cout << "Um_nik";
}