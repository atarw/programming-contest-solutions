#include <iostream>
#include <algorithm>
#include <stack>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	string s;
	cin >> s;
	
	stack <int> last;	
	
	for (int n = 0; n < s.size (); ++n) {
		if (s[n] == 'r')
			cout << n + 1 << '\n';
		else
			last.push (n + 1);
	}
	
	while (!last.empty ()) {
		cout << last.top () << '\n';
		last.pop ();
	}
}