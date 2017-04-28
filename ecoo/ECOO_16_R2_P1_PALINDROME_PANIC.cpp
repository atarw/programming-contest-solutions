#include <iostream>
#include <stdio.h>
#include <string.h>

using namespace std;

long long int hashf [1000001], hashb [1000001];
long long int _pow [1000001];
const int MOD = 1000000007, PRIME = 3417;

long long int hash_range (long long int _hash [], int l, int r) {
	return (_hash [r] - _hash [l - 1] * _pow [r - (l - 1)] % MOD + MOD) % MOD;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	string str;
	
	_pow [0] = 1;
	
	for (int i = 1; i <= 1000000; ++i)
		_pow [i] = (_pow [i - 1] * PRIME) % MOD;
	
	for (int t = 0; t < 10; ++t) {
		cin >> str;
		hashf [0] = hashb [0] = 0;
		
		for (int i = 1; i < str.length (); ++i) {
			hashf [i] = (hashf [i - 1] * PRIME + str [i]) % MOD;
			hashb [i] = (hashb [i - 1] * PRIME + str [str.length () - 1 - i]) % MOD;
		}
		
		int best = 0;
		
		for (int i = 1; i < str.length (); ++i)
			if (hash_range (hashf, 1, i) == hash_range (hashb, str.length () - i, str.length () - 1) || hash_range (hashb, 1, i) == hash_range (hashf, str.length () - i, str.length () - 1))
				best = max (best, i);
				
		cout << str.length () - (best + 1) << "\n";
	}
}
