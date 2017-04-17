#include <stdio.h>
#include <iostream>
#include <string.h>
#include <unordered_map>
#include <bitset>
#include <algorithm>
#include <queue>

using namespace std;

int N, Q;
int arr [100001];
bitset <1000> tree [400004];
unordered_map <int, int> comp;
unordered_map <int, int> frq;

void build (int i, int l, int r) {
	if (l == r) {
		tree [i][comp [arr [l]]] = true;
	}
	else {
		int mid = (l + r) / 2;
		build (i * 2 + 1, l, mid);
		build (i * 2 + 2, mid + 1, r);
		tree [i] = tree [i * 2 + 1] | tree [i * 2 + 2];
	}
}

void update (int i, int l, int r, int arr_i, int v, int old_cmp) {
	if (l == r) {
		tree [i][old_cmp] = false;
		tree [i][comp [v]] = true;
	}
	else {
		int mid = (l + r) / 2;
		
		if (arr_i <= mid)
			update (i * 2 + 1, l, mid, arr_i, v, old_cmp);
		else
			update (i * 2 + 2, mid + 1, r, arr_i, v, old_cmp);
		
		tree [i] = tree [i * 2 + 1] | tree [i * 2 + 2];
	}
}

bitset <1000> query (int i, int l, int r, int ql, int qr) {
	if (l == ql && r == qr)
		return tree [i];
	
	int mid = (l + r) / 2;
	
	if (qr <= mid)
		return query (i * 2 + 1, l, mid, ql, qr);
	
	if (ql >= mid + 1)
		return query (i * 2 + 2, mid + 1, r, ql, qr);

	return query (i * 2 + 1, l, mid, ql, mid) | query (i * 2 + 2, mid + 1, r, mid + 1, qr);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N >> Q;
	
	queue <int> nxt;
	
	for (int i = 0; i < 1000; ++i)
		nxt.push (i);
	
	for (int n = 0; n < N; ++n) {
		cin >> arr [n];
		
		if (!frq.count (arr [n]))
			frq [arr [n]] = 0;
		
		++frq [arr [n]];
		
		if (!comp.count (arr [n])) {
			comp [arr [n]] = nxt.front ();
			nxt.pop ();
		}
	}
	
	build (0, 0, N - 1);
	
	for (int q = 0, d, x, y; q < Q; ++q) {
		cin >> d >> x >> y;
		
		if (d == 1) {
			int old = arr [x - 1], old_cmp = comp [arr [x - 1]];
			
			if (frq [old] == 1) {
				frq.erase (old);
				nxt.push (comp [old]);
				comp.erase (old);
			}
			else {
				--frq [old];
			}
			
			if (frq.count (y)) {
				++frq [y];
			}
			else {
				frq [y] = 1;
				comp [y] = nxt.front ();
				nxt.pop ();
			}
			
			arr [x - 1] = y;
			update (0, 0, N - 1, x - 1, y, old_cmp);
		}
		else {
			cout << query (0, 0, N - 1, x - 1, y - 1).count () << "\n";
		}
	}
	
	return 0;
}