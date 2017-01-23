#include <stdio.h>

#define max(a,b) ((a) < (b) ? (b) : (a))

using namespace std;

int N;
double dp [1 << 21];
double arr [20][20];

double solve (int n, int missions) {
	if (dp [missions] != -1)
		return dp [missions];
	
	if (n == N)
		return dp [missions] = 1;
		
	dp [missions] = 0;
	
	for (int j = 0; j < N; ++j)
		if (!((missions >> j) & 1))
			dp [missions] = max (dp [missions], arr [n][j] * solve (n + 1, missions | 1 << j));
	
	return dp [missions];
}

int main () {
	scanf ("%d", &N);
	
	for (int n = 0; n < 1 << 21; ++n)
		dp [n] = -1;
			
	int val;
	
	for (int i = 0; i < N; ++i)	 {
		for (int j = 0; j < N; ++j) {
			scanf ("%d", &val); arr [i][j] = val / 100.0;
		}
	}

	printf ("%.6f", solve (0, 0) * 100);
}