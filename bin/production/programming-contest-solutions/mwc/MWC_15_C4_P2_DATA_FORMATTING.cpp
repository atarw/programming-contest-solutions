#include <stdio.h>
#include <vector>
#include <algorithm>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

vector <int> arr;

int main () {
	int N;
	scan (N);
	
	arr = vector <int> (N);
	
	for (int n = 0; n < N; n++) {
		scan (arr [n]);
	}
	
	sort (arr.begin (), arr.end ());
	
	int e = 0, o = N - 1;
	
	for (int n = 0; n < N; n++) {
		printf ("%d ", arr [n % 2 == 0 ? e : o]);
		
		if (n % 2 == 0) {
			e++;
		}
		else {
			o--;
		}
	}
}