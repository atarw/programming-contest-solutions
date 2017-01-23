#include <stdio.h>
#include <algorithm>
#include <vector>
#include <set>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

pair <int, int> arr [300000];
multiset <int> bag;

bool comp (const pair <int, int> &A, const pair <int, int> &B) {//first = M, second = V
	if (A.second != B.second) {
		return A.second > B.second;
	}
	else {
		return A.first < B.first;
	}
}

int main () {
	int N, K, M, V, C;
	scan (N); scan (K);
	
	for (int n = 0; n < N; n++) {
		scan (M); scan (V);
		arr [n].first = M;
		arr [n].second = V;
	}
	
	sort (arr, arr + N, comp);
	
	while (K--) {
		scan (C);
		bag.insert (C);
	}
	
	long long int total = 0;
	
	for (int n = 0; n < N && !bag.empty (); n++) {
		if (bag.lower_bound (arr [n].first) != bag.end ()) {
			total += arr [n].second;
			bag.erase (bag.lower_bound (arr [n].first));
		}
	}
	
	printf ("%lld", total);
}