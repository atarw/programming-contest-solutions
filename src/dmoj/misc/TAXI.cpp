#include <stdio.h>
#include <string.h>
#include <vector>
#include <queue>

#define INF 0x3f3f3f3f

using namespace std;

vector <vector <pair <int, int> > > list;

vector <int> djikstra (int s, int V) {
	vector <int> dist = vector <int> (V, INF);
	dist [s] = 0;
	
	queue <int> q;
	q.push (s);
	int u;
	
	while (!q.empty ()) {
		u = q.front (); q.pop ();
		
		for (pair <int, int> v : list [u]) {
			if (dist [v.first] > dist [u] + v.second) {
				dist [v.first] = dist [u] + v.second;
				q.push (v.first);
			}
		}
	}
	
	return dist;
}

int main () {
	int V, E, P, D, R;
	scanf ("%d %d %d %d %d", &V, &E, &P, &D, &R);
	list = vector <vector <pair <int, int> > > (V);
	
	for (int e = 0, a, b, c; e < E; ++e) {
		scanf ("%d %d %d", &a, &b, &c);
		list [a].push_back (make_pair (b, c));
		list [b].push_back (make_pair (a, c));
	}
	
	vector <int> arr1 = djikstra (P, V);
	vector <int> arr2 = djikstra (D, V);
	
	if (arr1 [D] == INF) {
		printf ("Nooooooooo!!!\n");
	}
	else {
		int cost = 3;
		
		if (arr1 [D] <= 10)
			cost += 2 * arr1 [D];
		else
			cost += 20 + arr1 [D] - 10;
			
		if (arr2 [R] == INF)
			printf ("%d\nYippee!!!\n", cost);
		else
			printf ("%d\n", cost + arr2 [R]);
	}
}