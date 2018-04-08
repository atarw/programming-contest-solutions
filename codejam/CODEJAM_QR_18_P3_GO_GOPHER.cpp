#include <iostream>
#include <algorithm>
#include <set>
#include <vector>

using namespace std;

bool dug[1001][1001];
int dirs[8][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
int sx, sy, ex, ey;

int unused (int x, int y) {
	int notdone = !dug[x][y];
	
	for (auto dir : dirs) {
		int nx = x + dir[0], ny = y + dir[1];
		
		if (!dug[nx][ny])
			++notdone;
	}
	
	return notdone;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	int T;
	cin >> T;
	
	for (int t = 0; t < T; ++t) {
		int A;
		cin >> A;
		
		for (int i = 0; i < 1001; ++i)
			for (int j = 0; j < 1001; ++j)
				dug[i][j] = false;
		
		int dimx = 1, dimy = A;
		int minp = 2 * (1 + A);
		
		for (int curdim = 1; curdim < A; ++curdim) {
			if (A % curdim)
				continue;
			
			if (2 * (curdim + A / curdim) < minp) {
				minp = 2 * (curdim + A / curdim);
				dimx = curdim;
				dimy = A / curdim;
			}
		}
		
		sx = 200; sy = 200;
		ex = sx + dimx - 1; ey = sy + dimy - 1;
		int cx = (sx + ex) / 2, cy = (sy + ey) / 2;
				
		vector <set <pair <int, int> > > nxt = vector <set <pair <int, int> > > (10);
		
		for (int i = sx; i <= ex; ++i)
			for (int j = sy; j <= ey; ++j)
				nxt[unused (i, j)].insert (make_pair (i, j));	
		
		vector <pair <int, int> > toremove;		
		
		while (true) {
			cout << cx << " " << cy << endl;
			
			int retx, rety;
			cin >> retx >> rety;
			
			if (retx == 0 && rety == 0) {
				break;
			}
			else if (retx == -1 && rety == -1) {
				return -1;
			}
			else {
				dug[retx][rety] = true;
				
				for (int f = 9; f >= 1; --f) {
					for (auto square : nxt[f]) {
						int sunused = unused (square.first, square.second);
						
						if (sunused != f) {
							nxt[sunused].insert (square);
							toremove.push_back (square);
						}
					}
					
					for (auto square : toremove)
						nxt[f].erase (square);
					
					toremove.clear ();
					
					if (nxt[f].empty ())
						continue;
					
					auto nxtsquare = nxt[f].begin ();
					cx = nxtsquare->first;
					cy = nxtsquare->second;
					break;
				}
			}
		}
	}
}