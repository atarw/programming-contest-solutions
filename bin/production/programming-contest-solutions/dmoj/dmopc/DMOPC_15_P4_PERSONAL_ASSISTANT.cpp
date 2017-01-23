#include <stdio.h>
#include <vector>
#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define lli long long int

using namespace std;

char _;

vector <lli> R, E, H;
vector <lli> cache;

lli best (int n) {
	if (cache [n] == 0 && n < H.size()) {
		cache [n] = max (H [n] + best (lower_bound (R.begin () + n, R.end (), E [n]) - R.begin ()), best (n + 1));
	}
	return cache [n];
}

int main () {
	int N;
	scan (N);
	
	R = vector <lli> (N);
	E = vector <lli> (N);
	H = vector <lli> (N);
	cache = vector <lli> (N + 1, 0);
	
	for (int n = 0; n < N; n++) {
		scanf ("%lld %lld %lld", &R [n], &E [n], &H [n]);
		E [n] += R [n];
	}
	
	printf ("%lld", best (0));
}