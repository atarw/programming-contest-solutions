#include <stdio.h>
#include <algorithm>
#include <vector>
#include <queue>
#include <string.h>
#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define fill(arr,v) memset(arr,v,sizeof(arr))

using namespace std;

char _;
const int INF = 0x3f3f3f3f;

int N, M;
char maze [21][21];
int cache [21][21][21][21];
vector <pair <int, int> > hidden = vector <pair <int, int> > ();

vector <pair <int, int> > next (int x, int y) {
	vector <pair <int, int> > v = vector <pair <int, int> > ();
	
	if (x + 1 < N && maze [x + 1][y] != 'X') {
		v.push_back (make_pair (x + 1, y));
	}
	
	if (x - 1 >= 0 && maze [x - 1][y] != 'X') {
		v.push_back (make_pair (x - 1, y));
	}
	
	if (y + 1 < M && maze [x][y + 1] != 'X') {
		v.push_back (make_pair (x, y + 1));
	}
	
	if (y - 1 >= 0 && maze [x][y - 1] != 'X') {
		v.push_back (make_pair (x, y - 1));
	}
	
	return v;
}

int main () {
	int N, M, T, x, y;
	
	scan (N);
	scan (M);
	scan (T);
	
	fill (cache, INF);
	
	for (int n = 0; n < N; n++) {
		for (int m = 0; m < M; m++) {
			scanf ("%c", &maze [n][m]);
			
			if (maze [n][m] == 'G') {
				x = n;
				y = m;
			}
			else if (maze [n][m] == 'H') {
				hidden.push_back (make_pair (n, m));
			}
			
			cache [n][m][n][m] = 0;
		}
	}
	
	queue <pair <int, int> > pq = queue <pair <int, int> > ();
	vector <pair <int, int> > v = vector <pair <int, int> > ();
	pair <int, int> curr = make_pair (x, y);
	
	pq.push (curr);
	
	while (!pq.empty ()) {
		curr = pq.front ();
		pq.pop ();
		
		v = next (curr.first, curr.second);
		
		for (int i = 0; i < v.size (); i++) {
			if (cache [x][y][v [i].first][v [i].second] > cache [x][y][curr.first][curr.second] + 1 || cache [curr.first][curr.second][v [i].first][v [i].second] > 1) {
				cache [x][y][v [i].first][v [i].second] = cache [v [i].first][v [i].second][x][y] = cache [x][y][curr.first][curr.second] + 1;
				pq.push (v [i]);
			}
			cache [curr.first][curr.second][v [i].first][v [i].second] = cache [v [i].first][v [i].second][curr.first][curr.second] = 1;
		}
	}
	
	
}