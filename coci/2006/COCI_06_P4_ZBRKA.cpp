#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const long long int MOD = 1000000007L;

int main(int argc, char *argv[]) {
	cin.sync_with_stdio();
	cin.tie(0);
	
	int N, C;
	cin >> N >> C;
	
	vector<long long int> top(C + 1), ps(C + 2, 1L);
	ps[0] = 0L;
	
	for (int n = 2; n <= N; ++n) {
		top[0] = 1;
		
		for (int c = 1; c <= C; ++c)
			top[c] = (ps[c + 1] - ps[max(0, c - (n - 1))]) % MOD;
		
		for (int c = 0; c <= C; ++c) {
			ps[c + 1] = ps[c] + top[c];
			top[c] = 0;
		}
	}
	
	cout << ps[C + 1] - ps[C];
}