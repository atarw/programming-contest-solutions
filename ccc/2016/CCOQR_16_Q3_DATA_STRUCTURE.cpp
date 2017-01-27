#include <stdio.h>
#include <algorithm>
#include <map>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a) < (b) ? (b) : (a))

char _;

using namespace std;

map <int, int> blocks;//column -> highest block on that column (max row value)

int main () {
	int N, M, r, c;
	
	scan (N); scan (M);
	
	for (int m = 0; m < M; ++m) {
		scan (r); scan (c); --c; r = N - r + 1;
		
		if (!blocks.count (c)) {
			blocks.insert (make_pair (c, r));
		}
		else {
			blocks [c] = max (blocks [c], r);
		}
	}

	long long int total = 0, height = 0;
	typename map <int, int>::iterator it;
	
	for (int n = 0; n < N; ++n) {//for each column
		if (blocks.count (n)) {//data found at location
			height = max (height, blocks [n]);
		}
		
		it = blocks.upper_bound (n);
		total = total + ((height * (height + 1)) / 2);
		
		if (it == blocks.end ()) {
			break;
		}
		else {
			if (it->first >= n + height) {//outside range
				height = 0;
			}
			else {//inside range
				height = height - (it->first - n);
				total = total - ((height * (height + 1)) / 2);
			}
			
			n = it->first - 1;
		}
	}
	
	printf ("%lld", total);
}