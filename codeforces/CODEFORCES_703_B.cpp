#include <iostream>

using namespace std;

bool cap [100000];
int val [100000], beauty [100000];

int main () {
	int N, K, c;
	long long int sum = 0, sum_c = 0, sum_n = 0;
	
	cin >> N >> K;
	
	for (int n = 0; n < N; ++n) {
		cin >> val [n];
	}
	
	for (int k = 0; k < K; ++k) {
		cin >> c;
		cap [c - 1] = true; beauty [k] = c - 1;
	}
	
	for (int n = 0; n < N; ++n) {
		(cap [n] ? sum_c : sum_n) += val [n];
	}
	
	for (int k = 0; k < K; ++k) {
		sum_c -= val [beauty [k]];
		sum += sum_c * val [beauty [k]];// c to c
		
		sum += sum_n * val [beauty [k]];// c to nc
	}
	
	for (int n = 0; n < N; ++n) {
		if (!cap [n] && !cap [(n + 1) % N]) {
			sum += val [(n + 1) % N] * val [n];// nc to nc
		}
	}
	
	cout << sum;
}