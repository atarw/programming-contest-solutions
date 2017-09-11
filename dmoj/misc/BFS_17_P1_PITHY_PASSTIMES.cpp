#include <iostream>
#include <algorithm>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	int cnt = 0;
	
	for (int n = 0; n < N; ++n) {
		string s;
		cin >> s;
		
		if (s.size() <= 10)
			++cnt;
	}
	
	cout << cnt;
}