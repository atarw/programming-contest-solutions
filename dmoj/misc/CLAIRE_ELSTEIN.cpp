#include <stdio.h>
#include <stdlib.h>
#include <vector>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

int main () {
	int N, M, i, j;
	scan (N); scan (M);
	
	//paths stores total number of paths that can be taken to n
	//cache stores total distance of paths to n
	
	vector <vector <int> > list = vector <vector <int> > (N);
	
	long long int *paths, *cache;
	paths = (long long int *) malloc (sizeof (long long int) * N); cache = (long long int *) malloc (sizeof (long long int) * N);
	
	for (int n = 0; n < N; n++) {
		paths [n] = 1;
	}
	
	while (M--) {
		scan (i); scan (j);
		i--; j--; paths [j] = 0;//will be set later through graph traversal, only points with indegree 0 will remain
		list [i].push_back (j);
	}
	
	long long int total = 0;
	
	for (int u = 0; u < N; u++) {//go through each node + neighbours
		if (!list [u].empty ()) {
			for (int v = 0; v < list [u].size (); v++) {
				paths [list [u][v]] += paths [u]; paths [list [u][v]] %= 1000000007;//paths to v = previously existing paths to v + paths to u
				cache [list [u][v]] += cache [u] + paths [u]; cache [list [u][v]] %= 1000000007;//total distance of paths to v = previously existing distance to v + total distance to u + total number ot paths to u
			}
		}
		else {//only nodes with outdegree 0
			total += cache [u]; total %= 1000000007;
		}
	}
	
	printf ("%lld", total % 1000000007);
}