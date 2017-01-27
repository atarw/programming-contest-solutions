#include <string.h>
#include <vector>

#define max(a,b) ((a) < (b) ? (b) : (a))
#define abs(a) ((a) < (0L) ? -(a) : (a))
#define INF 0x3f3f3f3f

using namespace std;

vector <vector <pair <int, int> > > list;
bool vis [100001], vis2 [100001]; // vis for dfs, vis2 for components
int dist [100001];

int furthest_node, furthest_dist;

void init (int N, int M, int A [], int B [], int T []) {
	list = vector <vector <pair <int, int> > > (N);

	for (int m = 0; m < M; ++m) {
		list [A [m]].push_back (make_pair (B [m], T [m]));
		list [B [m]].push_back (make_pair (A [m], T [m]));
	}
	
	memset (vis, false, sizeof (vis));
	memset (vis2, false, sizeof (vis2));
}

void dfs (int u) {
	vis [u] = true;
	vis2 [u] = true;
	
	if (dist [u] > furthest_dist) {
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

void pre (int u) {
	dist [u] = furthest_dist = 0;
	furthest_node = u;
}

pair <int, int> center (int a, int b, int diam) {// returns radius, center of tree
	int radius = 0, min_diff = INF, curr = b, next = -1, next_dist = 0, center = b;
	
	while (curr != a) {
		next = -1; next_dist = 0;
		
		for (int e = 0; e < list [curr].size (); ++e) {
			if (dist [list [curr][e].first] < dist [curr]) {
				next = list [curr][e].first;
				next_dist = dist [list [curr][e].first];
			}
		}
		
		if (min_diff > abs (diam - dist [curr] * 2)) {
			min_diff = abs (diam - dist [curr] * 2);
			radius = max (dist [curr], diam - dist [curr]);
			center = curr;
		}
		
		curr = next;
	}
	
	return make_pair (radius, center);
}

/* N nodes, M edges, L weight for new edges, ith edge joins A[i] and B[i], weight of T[i]
return max travel time between any pair of nodes after creation of N - M - 1 new edges (each with weight L) */

int travelTime (int N, int M, int L, int A [], int B [], int T []) {
	init (N, M, A, B, T);
	
	int a, b, diam;
	vector <pair <int, int> > centers;
	
	for (int n = 0; n < N; ++n) {
		if (!vis2 [n]) {
			pre (n); dfs (n);
			a = furthest_node;
			pre (a); dfs (a);
			
			b = furthest_node; diam = furthest_dist;
			centers.push_back (center (a, b, diam));
		}
	}
	
	int hub = 0; // tree center with max radius/eccentricity
	
	for (int n = 1; n < centers.size (); ++n)
		if (centers [hub].first < centers [n].first)
			hub = n;
	
	for (int n = 0; n < centers.size (); ++n) {
		if (n != hub) {
			list [centers [hub].second].push_back (make_pair (centers [n].second, L));
			list [centers [n].second].push_back (make_pair (centers [hub].second, L));
		}
	}
	
	pre (0); dfs (0);
	a = furthest_node;
	pre (a); dfs (a);
	
	return furthest_dist;
}