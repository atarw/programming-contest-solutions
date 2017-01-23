#include <stdio.h>
#include <string.h>
#include <queue>

using namespace std;

int main () {
	int N, M, sx, sy, ex, ey;
	scanf ("%d %d\n", &N, &M);
	
	char maze [N][M];
	
	for (int n = 0; n < N; ++n) {
		for (int m = 0; m < M; ++m) {
			maze [n][m] = getchar ();
			
			if (maze [n][m] == 's') {
				sx = n; sy = m;
			}
			else if (maze [n][m] == 'e') {
				ex = n; ey = m;
			}
		}
		getchar ();
	}
	
	int dir [4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	int dp [N][M];
	
	for (int n = 0; n < N; ++n)
		for (int m = 0; m < M; ++m)
			dp [n][m] = 1 << 20;

	dp [sx][sy] = 0;

	queue <pair <int, int> > q;
	pair <int, int> curr;
	q.push (make_pair (sx, sy));
	
	while (!q.empty ()) {
		curr = q.front (); q.pop ();
		
		for (auto &d : dir) {
			if (0 <= curr.first + d [0] && curr.first + d [0] < N && 0 <= curr.second + d [1] && curr.second + d [1] < M && dp [curr.first + d [0]][curr.second + d [1]] > dp [curr.first][curr.second] + 1 && maze [curr.first + d [0]][curr.second + d [1]] != 'X') {
				dp [curr.first + d [0]][curr.second + d [1]] = dp [curr.first][curr.second] + 1;
				q.push (make_pair (curr.first + d [0], curr.second + d [1]));
			}
		}
	}
	
	printf ("%d", dp [ex][ey] == 1 << 20 ? -1 : dp [ex][ey] - 1);
}