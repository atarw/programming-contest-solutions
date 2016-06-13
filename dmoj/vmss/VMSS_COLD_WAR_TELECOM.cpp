#include <stdio.h>
#include <string.h>
#include <vector>
#include <algorithm>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

vector <vector <int> > list;
vector <bool> visited, art;
vector <int> low, disc, parent;
int tm = 0, total = 0;

void dfs (int curr) {
	visited [curr] = true;
	tm++;
	low [curr] = disc [curr] = tm;
	
	int child = 0, next = 0;
	
	for (int i; i < list [curr].size (); i++) {
		next = list [curr][i];
		
		//if (visited [next]) {}//empty if statement somehow changes the entire program...on xcode only
		
		if (!visited [next]) {
			child++;
			parent [next] = curr;
			dfs (next);
			
			low [curr] = min (low [curr], low [next]);
			
			if (!art [curr] && ((parent [curr] != -1 && low [next] >= disc [curr]) || (parent [curr] == -1 && child > 1))) {
				art [curr] = true;
				total++;
			}
		}
		else if (next != parent [curr]) {
			low [curr] = min (low [curr], disc [next]);
		}
	}
}

int main () {
	int N, M, S, E;
	scan (N); scan (M);
	
	list = vector <vector <int> > (N);
	visited = vector <bool> (N);
	art = vector <bool> (N);
	low = vector <int> (N);
	disc = vector <int> (N);
	parent = vector <int> (N);
	
	for (int n = 0; n < N; n++) {
		parent [n] = -1;
		low [n] = disc [n] = 0;
		visited [n] = art [n] = false;
	}
	
	while (M--) {
		scan (S); scan (E);
		S--; E--;
		
		list [S].push_back (E);
		list [E].push_back (S);
	}
	
	dfs (0);
	
	printf ("%d\n", total);
	
	for (int n = 0; n < N; n++) {
		if (art [n]) {
			printf ("%d\n", n + 1);
		}
	}
	
	return 0;
}