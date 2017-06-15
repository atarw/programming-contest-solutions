#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

unordered_map<int, unordered_map<int, unordered_map<int, bool> > > dp;
string codes [4];
unordered_set <int> pos [4];
int cnt = 0;
string ln;

int find (int c, int i) {
	if (i + codes[c].size () > ln.size ())
		return -1;
	
	for (; i + codes[c].size () <= ln.size (); ++i)
		if (ln.substr (i, codes[c].size ()) == codes[c])
			return i;
			
	return -1;
}

void solve (int i, int x, int y) {
	if (dp[i][x].count (y))
		return;
		
	dp[i][x][y] = true;
	
	if (i == ln.size ()) {
		++cnt;
		return;
	}
	
	for (int c = 0; c < 4; ++c)
		if (pos [c].count (i))
			solve (i + codes[c].size (), x + (c == 0 ? 1 : c == 1 ? -1 : 0), y + (c == 2 ? -1 : c == 3 ? 1 : 0));
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	for (int i = 0; i < 4; ++i)
		cin >> codes[i];
	
	cin >> ln;
	
	for (int i = 0; i < 4; ++i) {
		int idx = 0;

		while (true) {
			idx = find (i, idx);

			if (idx == -1)
				break;

			pos[i].insert (idx);
			++idx;
		}
	}

	solve (0, 0, 0);
	cout << cnt;
}