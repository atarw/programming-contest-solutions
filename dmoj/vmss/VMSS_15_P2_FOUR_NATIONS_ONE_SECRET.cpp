#include <iostream>
#include <stdio.h>
#include <string.h>

using namespace std;

long long int hashf [1000001], hashb [1000001];
long long int _pow [25001];
const int MOD = 1000000007, PRIME = 3417;
int L;
string str;

long long int hash_range (long long int _hash [], int l, int r) {
	return (_hash [r] - _hash [l - 1] * _pow [r - (l - 1)] % MOD + MOD) % MOD;
}

int palindrome (int x) {
	for (int l = 1; l + x < L; ++l)
		if (hash_range (hashf, l, l + x) == hash_range (hashb, str.length () - l - x, str.length () - l))
			return l - 1;
	
	return -1;
}

pair <int, int> upper_bound () {
	int low = 0, high = L - 1, mid = -1, last = -1;
	int best_idx = 0, idx = 0;
	
	while (low < high) {
		mid = (low + high) / 2;
		idx = palindrome (mid);
		
		if (idx == -1)
			high = mid;
		else {
			low = mid + 1;
			
			if (last < mid)
				best_idx = idx;
			
			last = mid;
		}
	}
	
	return make_pair (best_idx, low + 1);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	cin >> L;
	cin >> str;
	
	_pow [0] = 1;
	hashf [0] = hashb [0] = 0;
	
	for (int i = 1; i <= 25000; ++i)
		_pow [i] = (_pow [i - 1] * PRIME) % MOD;
	
	for (int i = 1; i < str.length (); ++i) {
		hashf [i] = (hashf [i - 1] * PRIME + str [i]) % MOD;
		hashb [i] = (hashb [i - 1] * PRIME + str [str.length () - 1 - i]) % MOD;
	}
	
	pair <int, int> ans = upper_bound (); // first = start, second = length
	
	for (int i = ans.first; i < ans.first + ans.second; ++i)
		cout << str [i];
	
	cout << "\n" << ans.second;
}
