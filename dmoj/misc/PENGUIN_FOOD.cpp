#include <iostream>
#include <algorithm>

using namespace std;

int N;
int arr [1000001];
struct node {
	long long best, sum, pref, suf;
} tree [4000001];

node merge (node l, node r) {
	node n = {};
	n.sum = l.sum + r.sum;
	n.pref = max (l.pref, l.sum + r.pref);
	n.suf = max (r.suf, r.sum + l.suf);
	n.best = max (max (l.best, r.best), l.suf + r.pref);
	
	return n;
}

void build (int i, int l, int r) {
	if (l == r) {
		node n = {arr [l], arr [l], arr [l], arr [l]};
		tree [i] = n;
	}
	else {
		int mid = (l + r) / 2;
		build (i * 2 + 1, l, mid);
		build (i * 2 + 2, mid + 1, r);
		tree [i] = merge (tree [i * 2 + 1], tree [i * 2 + 2]);
	}
}

void update (int i, int l, int r, int treei, int v) {
	if (l == r) {
		arr [treei] = v;
		node n = {v, v, v, v};
		tree [i] = n;
	}
	else {
		int mid = (l + r) / 2;
		
		if (treei <= mid)
			update (i * 2 + 1, l, mid, treei, v);
		else
			update (i * 2 + 2, mid + 1, r, treei, v);
			
		tree [i] = merge (tree [i * 2 + 1], tree [i * 2 + 2]);
	}
}

node query (int i, int l, int r, int ql, int qr) {
	if (l == ql && r == qr)
		return tree [i];
		
	int mid = (l + r) / 2;
	
	if (qr <= mid)
		return query (i * 2 + 1, l, mid, ql, qr);
	else if (ql > mid)
		return query (i * 2 + 2, mid + 1, r, ql, qr);
	
	return merge (query (i * 2 + 1, l, mid, ql, mid), query (i * 2 + 2, mid + 1, r, mid + 1, qr));
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	cin >> N;
	
	for (int n = 0; n < N; ++n)
		cin >> arr [n];
	
	build (0, 0, N - 1);
	long long ans = query (0, 0, N - 1, 0, N - 1).best;
	cout << (ans < 0 ? 0 : ans) << "\n";
	
	int M;
	cin >> M;
	
	for (int m = 0; m < M; ++m) {
		int x, y;
		cin >> x >> y;
		update (0, 0, N - 1, x, y);
		ans = query (0, 0, N - 1, 0, N - 1).best;
		cout << (ans < 0 ? 0 : ans) << "\n";
	}
}