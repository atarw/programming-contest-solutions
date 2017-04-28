#include <iostream>
#include <stdio.h>
#include <unordered_map>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	while (true) {
		int N, M;
		
		cin >> N >> M;
		
		if (!N && !M)
			break;
		
		unordered_map <int, int> map;
		
		for (int n = 0, a; n < N + M; ++n) {
			cin >> a;
			++map [a];
		}
		
		int cnt = 0;
		
		for (auto a : map)
			if (!(a.second & 1))
				++cnt;
		
		cout << cnt << '\n';
	}
}
