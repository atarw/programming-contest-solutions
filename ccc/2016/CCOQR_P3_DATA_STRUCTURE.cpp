#include <stdio.h>
#include <unordered_map>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define min(a,b) ((a) < (b) ? (a) : (b))

char _;

using namespace std;

unordered_map <int, int> points;

int main () {
	
	int N, M, r, c, p = 0;
	
	scan (N); scan (M);
	points.reserve (M);
	
	for (int m = 0; m < M; m++) {
		scan (r); scan (c);
		r--; c--;
		
		if (points.find (c) != points.end ()) {
			points [c] = min (points [c], r);
		}
		else {
			points.insert (make_pair (c, r));
		}
	}
	
	long long int sum = 0;
	int highest = N;
	
	for (int n = 0; n < N; n++) {
		
		if (points.find (n) != points.end () && highest > points [n]) {
			highest = points [n];
			p++;
		}
		
		sum += (N - highest);
		
		if (highest < N) {
			++highest;
		}
		
		if (highest == N && p == M) {
			break;
		}
	}
	
	printf ("%lld", sum);
}