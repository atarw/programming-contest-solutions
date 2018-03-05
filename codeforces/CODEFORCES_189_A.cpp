#include <iostream>
#include <math.h>
#include <algorithm>

using namespace std;

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, A, B, C;
	cin >> N >> A >> B >> C;
	
	int best = 0;
	
	for (int x = 0; x <= N; ++x) {
		for (int y = 0; y <= N; ++y) {
			int Cz = N - A * x - B * y;
			
			if (Cz >= 0 && Cz % C == 0) {
				int z = Cz / C;
				best = max (best, x + y + z);
			}
		}
	}
	
	cout << best;
}