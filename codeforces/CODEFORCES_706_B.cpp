#include <stdio.h>
#include <string.h>
#include <vector>
#include <algorithm>

char _;

using namespace std;

int main () {
	int N;
	scanf ("%d", &N);
	
	vector <int> price = vector <int> (N);
	
	for (int n = 0; n < N; ++n)
		scanf ("%d", &price [n]);
	
	sort (price.begin (), price.end ());
	
	int Q; scanf ("%d", &Q);
	
	while (Q --> 0) {
		int m; scanf ("%d", &m);
		printf ("%ld\n", upper_bound (price.begin (), price.end (), m) - price.begin ());
	}

	return 0;
}