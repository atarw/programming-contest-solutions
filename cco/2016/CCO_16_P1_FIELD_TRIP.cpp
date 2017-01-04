#include <stdio.h>
#include <unordered_set>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

int parent [1000001], rank [1000001], size [1000001], edges [1000001];

int find (int u) {
	if (parent [u] != u)
		return parent [u] = find (parent [u]);
		
	return parent [u];
}

void merge (int u, int v) {
	int r1 = find (u), r2 = find (v);
	
	if (r1 != r2) {
		if (rank [r1] >= rank [r2]) {
			parent [r2] = r1;
			size [r1] += size [r2];
			edges [r1] += edges [r2] + 1;
			
			if (rank [r1] == rank [r2])
				++rank [r1];
		}
		else {
			parent [r1] = r2;
			size [r2] += size [r1];
			edges [r2] += edges [r1] + 1;
		}
		find (u); find (v);
	}
	else {
		++edges [r1];
	}
}

int main () {
	int N, M, K;
	scan (N); scan (M); scan (K);
	
	for (int n = 0; n < N; ++n) {
		parent [n] = n; rank [n] = edges [n] = 0; size [n] = 1;
	}
	
	for (int m = 0, a, b; m < M; ++m) {
		scan (a); scan (b);
		merge (a - 1, b - 1);
	}
	
	std::unordered_set <int> roots; roots.reserve (N);
	
	for (int n = 0; n < N; ++n)
		roots.insert (find (n));
	
	int students = 0, broke = 0;
	
	for (int root : roots) {
		if (size [root] >= K) {
			students += size [root] - size [root] % K;
			broke += (size [root] - 1) / K + (size [root] == edges [root] && size [root] != K);
		}
	}
	
	printf ("%d %d", students, broke);
}