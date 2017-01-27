#include <stdio.h>
#include <string.h>
#include <vector>
#include <unordered_map>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)

char _;

using namespace std;

int binary_search_ceil (int arr [], int from, int to, int val) {
	int mid;
	
	while (to - from > 1) {
		mid = from + (to - from) / 2;
		
		if (arr [mid] >= val)
			to = mid;
		else
			from = mid;
	}
	
	return to;
}

int LIS (vector <int> list) {
	int dp [list.size ()];
	memset (dp, 0, sizeof (dp));
	dp [0] = list [0];
	
	int size = 1;
	
	for (int n = 1; n < list.size () && size < list.size (); ++n) {
		if (list [n]< dp [0])
			dp [0] = list [n];
			
		else if (list [n] > dp [size - 1]) {
			dp [size] = list [n]; ++size;
		}
			
		else
			dp [binary_search_ceil (dp, -1, size - 1, list [n])] = list [n];
	}
	
	return size;
}

int main () {
	int N, N2;
	scan (N);
	
	unordered_map <int, int> map;
	
	for (int n = 0, v; n < N; ++n) {
		scan (v);
		map.insert (make_pair (v, n));
	}
	
	scan (N2);
	vector <int> list;
	
	for (int n = 0, v; n < N2; ++n) {
		scan (v);
		
		if (map.count (v))
			list.push_back (map [v]);
	}
	
	printf ("%d", list.empty () ? 0 : LIS (list));
}