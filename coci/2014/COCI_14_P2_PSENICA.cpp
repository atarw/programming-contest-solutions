#include <stdio.h>
#include <map>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

int main () {
	int N, s;
	
	scan (N);
	map <int, int> stalks;
	
	while (N--) {
		scan (s);
		
		if (stalks.find (s) == stalks.end ()) {
			stalks [s] = 1;
		}
		else {
			stalks [s]++;
		}
	}

	int turn = 0;
	map <int, int>::iterator it;
	map <int, int>::reverse_iterator it2;
	
	while (stalks.size () >= 3) {
		if (turn) {//slavko
			it2 = stalks.rbegin ();
			it = stalks.lower_bound (it2->first);
			
			if (it != stalks.begin ()) {
				--it;
			}
			
			stalks [it->first]++;
			
			if (stalks [it2->first] == 1) {
				stalks.erase (it2->first);
			}
			else {
				stalks [it2->first]--;
			}
		}
		else {
			it = stalks.upper_bound (stalks.begin ()->first);
			
			stalks [it->first]++;
			it = stalks.begin ();
			
			if (stalks [it->first] == 1) {
				stalks.erase (it->first);
			}
			else {
				stalks [it->first]--;
			}
		}
		
		turn ^= 1;
	}
	
	if (!turn) {
		printf ("Slavko\n");
	}
	else {
		printf ("Mirko\n");
	}
	
	printf ("%d %d", stalks.begin ()->first, stalks.rbegin ()->first);
}