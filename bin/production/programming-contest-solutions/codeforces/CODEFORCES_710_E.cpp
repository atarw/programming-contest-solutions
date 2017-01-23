#include <iostream>
#include <string.h>
#define min(a,b) ((a) > (b) ? (b) : (a))

using namespace std;

long long int cache [21000005];
int N, X, Y;

int main () {
	cin.sync_with_stdio (0); cin.tie (0);
	cin >> N >> X >> Y;
	
	cache [1] = X;
	
	for (int n = 2; n <= N; ++n) {
		cache [n] = cache [n - 1] + X;
		cache [n] = min (cache [n], cache [n - 1] + X);
		cache [n] = min (cache [n], cache [(n + 1) / 2] + Y + (n % 2) * X);
	}
	
	cout << cache [N];
}