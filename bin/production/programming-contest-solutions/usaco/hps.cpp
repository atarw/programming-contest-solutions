#include <stdio.h>
#include <string.h>
#include <iostream>
#include <fstream>

using namespace std;

int N, K;
int moves [100001];
int dp [100001][21][3];
int pref [100001][3];

inline int max (int a, int b) {
	return a > b ? a : b;
}

inline int win (int cow, int fj) {
	return cow == 0 && fj == 2 || cow == 1 && fj == 0 || cow == 2 && fj == 1;
}
	
int solve (int n, int k, int m) {
	if (dp [n][k][m] != -1)
		return dp [n][k][m];
	
	if (n == N)
		return dp [n][k][m] = 0;
	
	if (k == 0)
		return dp [n][k][m] = pref [N][m] - pref [n][m];
	
	dp [n][k][m] = win (m, moves [n]) + solve (n + 1, k, m);
	
	for (int mm = 0; mm < 3; ++mm)
		dp [n][k][m] = max (dp [n][k][m], win (m, moves [n]) + solve (n + 1, k - 1, mm));
	
	return dp [n][k][m];
}

int main () {
	ifstream f; f.open ("hps.in");
	ofstream f2; f2.open ("hps.out");
	
	f >> N >> K;
	
	string chr;
	
	for (int n = 0; n < N; ++n) {
		f >> chr;
		moves [n] = (chr [0] == 'H' ? 0 : chr [0] == 'P' ? 1 : 2);
	}
	
	for (int n = 0; n <= N; ++n)
		for (int k = 0; k <= K; ++k)
			for (int m = 0; m <= 2; ++m)
				dp [n][k][m] = -1;

	pref [0][0] = pref [0][1] = pref [0][2] = 0;
	
	for (int n = 1; n <= N; ++n)
		for (int m = 0; m < 3; ++m)
			pref [n][m] = pref [n - 1][m] + win (m, moves [n - 1]);
	
	f2 << max (max (solve (0, K, 0), solve (0, K, 1)), solve (0, K, 2)) << endl;
	f.close ();
	f2.close ();
}