#include <vector>
#include <queue>
#include <deque>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define INF 9223372036854675807

char _;

using namespace std;

vector <vector <pair <int, int> > > list;
long long int cache [100000];
int path [100000];

int main () {
	int N, M;
	
	scan (N); scan (M);

	list = vector <vector <pair <int, int> > > (N);
	
	for (int m = 0, a, b, w; m < M; ++m) {
		scan (a); scan (b); scan (w);
		--a; --b;
		
		list [a].push_back (make_pair (b, w));
		list [b].push_back (make_pair (a, w));
	}
	
	for (int n = 0; n < N; ++n) {
		cache [n] = INF;
		path [n] = -1;
	}
	
	cache [0] = 0;
	
	queue <int> q;
	q.push (0);
	
	int curr = 0;
	
	while (!q.empty ()) {
		curr = q.front (); q.pop ();
		
		if (!list [curr].empty ()) {
			for (int e = 0; e < list [curr].size (); ++e) {
				if (cache [list [curr][e].first] > cache [curr] + list [curr][e].second) {
					cache [list [curr][e].first] = cache [curr] + list [curr][e].second;
					path [list [curr][e].first] = curr;
					
					if (list [curr][e].first != N - 1 && cache [list [curr][e].first] < cache [N - 1]) {
						q.push (list [curr][e].first);
					}
				}
			}
		}
	}
	
	if (cache [N - 1] == INF) {
		puts ("-1");
	}
	else {
		deque <int> route;
		
		for (int v = N - 1; v != -1; v = path [v]) {
			route.push_front (v);
		}
		
		while (!route.empty ()) {
			printf ("%d ", route.front () + 1);
			route.pop_front ();
		}
	}
}