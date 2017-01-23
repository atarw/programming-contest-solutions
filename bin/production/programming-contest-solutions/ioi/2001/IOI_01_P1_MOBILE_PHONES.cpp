#include <stdio.h>
#include <string.h>

using namespace std;

int bit [1030][1030];

inline void update (int x, int y, int v, int s) {
	for (int xx = x; xx <= s; xx += xx & -xx)
		for (int yy = y; yy <= s; yy += yy & -yy)
			bit [xx][yy] += v;
}

inline int query (int x, int y) {
	int sum = 0;
	
	for (int xx = x; xx > 0; xx -= xx & -xx)
		for (int yy = y; yy > 0; yy -= yy & -yy)
			sum += bit [xx][yy];

	return sum;
}

int main () {
	memset (bit, 0, sizeof (bit));
	int c, s, x, y, a, l, b, r, t;
	
	while (true) {
		scanf ("%d ", &c);
		
		if (c == 0) {
			scanf ("%d\n", &s);
		}
		else if (c == 1) {
			scanf ("%d %d %d\n", &x, &y, &a);
			update (x + 1, y + 1, a, s);
		}
		else if (c == 2) {
			scanf ("%d %d %d %d\n", &l, &b, &r, &t);
			printf ("%d\n", query (r + 1, t + 1) - query (r + 1, b) - query (l, t + 1) + query (l, b));
		}
		else {
			break;
		}
	}
}