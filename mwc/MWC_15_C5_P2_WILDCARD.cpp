#include <stdio.h>
#include <unordered_map>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

int main () {
	int N, K, W, T, pairs = 0;
	scan (N); scan (K); scan (T);
	
	const char *players [] = {"kushanzaveri", "aurpine"};
	unordered_map <int, int> map;
	
	for (int n = 0; n < N; n++) {
		scan (W);
		
		if (map.find (K - W) != map.end ()) {
			if (map [K - W] == 1) {
				map.erase (K - W);
			}
			else {
				map [K - W]--;
			}
			
			T ^= 1;
			pairs++;
		}
		else {
			if (map.find (W) == map.end ()) {
				map.insert (pair <int, int> (W, 1));
			}
			else {
				map [W]++;
			}
		}
	}
	
	printf ("%s %d", players [T], pairs / 2 + pairs % 2);
}