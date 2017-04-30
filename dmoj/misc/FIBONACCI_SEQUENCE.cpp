#include <stdio.h>
#include <iostream>
#include <unordered_map>

#define ulli unsigned long long int

using namespace std;

const int MOD = 1000000007;
unordered_map <ulli, ulli> dp;

inline ulli add (ulli a, ulli b) {
	return ((a % MOD) + (b % MOD)) % MOD;
}

inline ulli mult (ulli a, ulli b) {
	return ((a % MOD) * (b % MOD)) % MOD;
}

inline ulli f (ulli n) {
	if (dp.count (n))
		return dp [n] % MOD;
	
	ulli nn = n / 2L;
	
	if (n % 2L == 0L)
		return dp [n] = add (mult (f (nn), f (nn)), mult (f (nn - 1), f (nn - 1)));
	else
		return dp [n] = add (mult (f (nn), f (nn + 1)), mult (f (nn), f (nn - 1)));
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	ulli N;
	cin >> N;
	
	dp [0] = dp [1] = 1L;
	cout << f (N - 1L);
}
