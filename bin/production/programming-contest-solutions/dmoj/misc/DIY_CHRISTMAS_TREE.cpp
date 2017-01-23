#include <stdio.h>
#include <vector>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

vector <vector <int> > list;
vector <int> order;
int hist = 1;

void post (int u) {
	order [u] = hist;
	hist++;
	
	for (int v = list [u].size () - 1; v >= 0; v--) {
		post (list [u][v]);
	}
}

void pre (int u) {
	printf ("%d ", order [u]);
	
	for (int v = 0; v < list [u].size (); v++) {
		pre (list [u][v]);
	}
}

int main () {
	int N, U, V;
	scan (N);
	
	list = vector <vector <int> > (N);
	order = vector <int> (N);
	
	for (int n = 0; n < N; n++) {
		scan (U);
		
		for (int u = 0; u < U; u++) {
			scan (V);
			V--;
			list [n].push_back (V);
		}
	}
	
	post (0);
	pre (0);
}