int adj [1501][1501];
int parent [1501];
int sz;

void merge (int u, int v) {
	for (int n = 0; n < sz; ++n)
		if (parent [n] == v)
			parent [n] = u;
	
	for (int n = 0; n < sz; ++n)
		adj [u][n] = adj [n][u] = adj [u][n] + adj [v][n];
}

void initialize (int N) {
	sz = N;
		
	for (int n = 0; n < N; ++n)
		for (int n2 = 0; n2 < N; ++n2)
			adj [n][n2] = n != n2;
				
	for (int n = 0; n < N; ++n)
		parent [n] = n;
}

int hasEdge (int u, int v) {
	int r1 = parent [u], r2 = parent [v];
	
	if (adj [r1][r2] > 1) {
		--adj [r1][r2];
		--adj [r2][r1];
		return 0;
	}
	
	adj [r1][r2] = adj [r2][r1] = 0;
	merge (r1, r2);
	return 1;
}