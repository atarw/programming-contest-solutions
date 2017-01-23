#include <stdio.h>
#include <string.h>
#include <vector>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a) < (b) ? (b) : (a))
#define abs(a) ((a) < (0L) ? -(a) : (a))
#define INF 0x3f3f3f3f

char _;

using namespace std;

vector <vector <pair <int, int> > > list;
long long int dist [100001];
bool vis [100001], vis2 [100001]; // vis keeps track of nodes visited during dfs, vis2 keeps track of visited components

int furthest_node;
long long int furthest_dist;

void dfs (int u) {
	vis [u] = vis2 [u] = true;
	
	if (furthest_dist < dist [u]) {
		furthest_dist = dist [u];
		furthest_node = u;
	}
	
	for (int e = 0; e < list [u].size (); ++e) {
		if (!vis [list [u][e].first]) {
			dist [list [u][e].first] = dist [u] + list [u][e].second;
			dfs (list [u][e].first);
		}
	}
	
	vis [u] = false;
}

void pre (int s) {
	dist [s] = 0L;
	furthest_node = s;
	furthest_dist = 0;
}

int main () {
	int N, M, Q;
	
	scan (N); scan (M); scan (Q);
	list = vector <vector <pair <int, int> > > (N);
	memset (vis, false, sizeof (vis));
	memset (vis2, false, sizeof (vis2));
	
	for (int m = 0, s, e, w; m < M; ++m) {
		scan (s); scan (e); scan (w);
		--s; --e;
		
		list [s].push_back (make_pair (e, w));
		list [e].push_back (make_pair (s, w));
	}
	
	bool mult = false;
	int a, curr, next, comp = 0;
	long long int radius, max_radius = 0L, min_diff, next_dist, sum = 0L;
	
	for (int n = 0; n < N; ++n) {
		if (!vis2 [n]) {
			++comp;
			
			pre (n); dfs (n);
			a = furthest_node;
			pre (a); dfs (a);
			
			if (Q == 2) {
				curr = furthest_node;

				radius = 0L;
				min_diff = INF;
				
				next = -1;
				next_dist = 0;
				
				while (curr != a) {
					for (int e = 0; e < list [curr].size (); ++e) {
						if (dist [list [curr][e].first] < dist [curr]) {
							next_dist = dist [list [curr][e].first];
							next = list [curr][e].first;
						}
					}
					
					if (min_diff > abs (furthest_dist - dist [curr] * 2L)) {
						min_diff = abs (furthest_dist - dist [curr] * 2L);
						radius = max (dist [curr], furthest_dist - dist [curr]);
					}
					
					curr = next;
				}
				
				if (max_radius < radius) {
					max_radius = radius;
					mult = false;
				}
				else if (max_radius == radius) {
					mult = true;
				}
			}
			else {
				sum += furthest_dist;
			}
		}
	}

	if (Q == 1) {// largest height
		printf ("%lld", sum + comp - 1);
	}
	else {// smallest height
		printf ("%lld", max_radius + mult);
	}
}