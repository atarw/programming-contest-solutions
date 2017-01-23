#include <stdio.h>
#include <queue>
#include <unordered_map>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define ui unsigned int

using namespace std;

char _;
unordered_map <ui, int> cache;
int K;

inline int get (ui num, int i) {
	return (num >> i) & 1U;
}

inline ui set (ui num, int i) {
	return num | (1U << i);
}

inline ui clear (ui num, int i) {
	return num & ~(1U << i);
}

inline ui check (ui num) {
	int cons = 0;
	
	for (int k = 0; k < K; ++k) {
		if (get (num, k)) {
			++cons;
		}
		else {
			if (cons >= 4) {
				num &= ~(((1 << cons) - 1) << (k - cons));
			}
			
			cons = 0;
		}
	}
		
	if (cons >= 4) {
		num &= ~(((1 << cons) - 1) << (K - cons));
	}
		
	return num;
}

int main () {
	cache.reserve (1010662);
	
	int bit;
	ui num = 0U;
	
	scan (K);
	
	for (int k = 0; k < K; ++k) {
		scan (bit);
		
		if (bit) num = set (num, k);
		else num = clear (num, k);
	}
	
	queue <ui> q;
	ui curr = num, next;
	
	q.push (curr);
	cache.insert (pair <ui, int> (num, 0));
	cache [num] = 0;
	
	while (!q.empty ()) {
		curr = q.front (); q.pop ();
		
		if (curr == 0) {
			break;
		}

		for (int k = 0; k < K; ++k) {
			if (!get (curr, k)) {
				next = check (set (curr, k));
				
				if (!cache.count (next)) {
					cache.insert (pair <ui, int> (next, cache [curr] + 1));
					q.push (next);
				}
			}
		}
	}
	
	printf ("%d", cache [0]);
}