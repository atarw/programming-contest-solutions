#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
char grid[50][50];
int vis[50][50];
int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

bool dfs (int x, int y, int d) {
	vis[x][y] = d;
	
	for (auto di : dir) {
		int nx = x + di[0], ny = y + di[1];
		
		if (0 <= nx && nx < N && 0 <= ny && ny < M && grid[nx][ny] == grid[x][y])
			if (vis[nx][ny] && vis[x][y] - vis[nx][ny] >= 3 || !vis[nx][ny] && dfs (nx, ny, d + 1))
				return true;
	}
	
	vis[x][y] = 0;
	return false;
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	cin >> N >> M;
	
	for (int n = 0; n < N; ++n)
		for (int m = 0; m < M; ++m)
			cin >> grid[n][m];
	
	bool cycle = false;
	
	for (int n = 0; n < N && !cycle; ++n)
		for (int m = 0; m < M && !cycle; ++m)
			if (dfs (n, m, 1))
				cycle = true;
	
	cout << (cycle ? "Yes" : "No");
}