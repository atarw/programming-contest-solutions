#include <stdio.h>
#include <string.h>
#include <queue>
#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define fill(arr,v) memset(arr,v,sizeof(arr))

using namespace std;

char _;
const int INF = 0x3f3f3f3f;

int main () {
	int L, D;
	
	scan (L);
	scan (D);
	
	int maze [D][L];
	int cache [D][L];
	
	for (int d = 0; d < D; d++) {
		for (int l = 0; l < L; l++) {
			scan (maze [d][l]);
		}
	}
	
	queue <pair <int, int> > pq = queue <pair <int, int> > ();
	pair <int, int> curr = pair <int, int> (0, 0);
	pq.push (curr);
	
	fill (cache, INF);
	cache [0][0] = maze [0][0];
	
	while (!pq.empty ()) {
		curr = pq.front ();
		pq.pop ();
		
		if (curr.first + 1 < D && cache [curr.first + 1][curr.second] > cache [curr.first][curr.second] + maze [curr.first + 1][curr.second]) {//down
			cache [curr.first + 1][curr.second] = cache [curr.first][curr.second] + maze [curr.first + 1][curr.second];
			pq.push (make_pair (curr.first + 1, curr.second));
		}
		
		if (curr.second - 1 >= 0 && cache [curr.first][curr.second - 1] > cache [curr.first][curr.second] + maze [curr.first][curr.second - 1]) {//left
			cache [curr.first][curr.second - 1] = cache [curr.first][curr.second] + maze [curr.first][curr.second - 1];
			pq.push (make_pair (curr.first, curr.second - 1));
		}
		
		if (curr.second + 1 < L && cache [curr.first][curr.second + 1] > cache [curr.first][curr.second] + maze [curr.first][curr.second + 1]) {//right
			cache [curr.first][curr.second + 1] = cache [curr.first][curr.second] + maze [curr.first][curr.second + 1];
			pq.push (make_pair (curr.first, curr.second + 1));
		}
	}
	
	int endX, endY;
	scan (endX);
	scan (endY);
	
	printf ("%d", cache [endY][endX]);
}