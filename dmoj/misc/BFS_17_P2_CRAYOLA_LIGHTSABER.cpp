#include <iostream>
#include <unordered_map>
#include <algorithm>
#include <string.h>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N;
	cin >> N;
	
	unordered_map<string, int> m;
	m["red"] = 0;
	m["orange"] = 1;
	m["yellow"] = 2;
	m["green"] = 3;
	m["blue"] = 4;
	m["black"] = 5;
	
	int cnt [6];
	memset (cnt, 0, sizeof (cnt));
	
	for (int n = 0; n < N; ++n) {
		string s;
		cin >> s;
		++cnt[m[s]];
	}
	
	int l = 0, last = -1;
	sort(cnt, cnt + 6);
	
	for (int i = 5; i >= 0; --i) {
		if (cnt [i] != 0) {
			if (last != i) {
				last = i;
				++l;
				--cnt[i];
				i = 6;
			}
		}
	}
	
	cout << l;
}