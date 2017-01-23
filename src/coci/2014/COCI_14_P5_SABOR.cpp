#include <stdio.h>
#include <string.h>
#include <vector>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

vector <bool> arr;
vector <vector <int> > list;
vector <int> inter;

int main () {
	int N; scan (N);
	
	list = vector <vector <int> > (N);
	arr = vector <bool> (N);
	inter = vector <int> (N);
	
	for (int n = 0; n < N; n++) {
		arr [n] = false;
	}
	
	int P, S, E;
	
	for (int d = 0; d < 5; d++) {
		scan (P);
		
		for (int p = 0; p < P; p++) {
			scan (S); scan (E);
			S--; E--;
			
			list [S].push_back (E);
			list [E].push_back (S);
			
			inter [S]++; inter [E]++;
		}
	}
	
	while (true) {
		bool change = false;
		
		for (int c = 0; c < N; c++) {
			if (inter [c] > 2) {
				arr [c] = !arr [c];
				change = true;
				
				for (int i = 0; i < list [c].size (); i++) {
					if (arr [c] == arr [list [c][i]]) {
						inter [c]++;
						inter [list [c][i]]++;
					}
					else {
						inter [c]--;
						inter [list [c][i]]--;
					}
				}
			}
		}
		
		if (!change) {
			break;
		}
	}
	
	for (int n = 0; n < N; n++) {
		printf ("%c", !arr [n] ? 'B' : 'A');
	}
}