#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> tree, arr;

inline int left (int i) {
	return 2 * i + 1;
}

inline int right (int i) {
	return 2 * i + 2;
}

inline pair<int, bool> merge (int i, pair<int, bool> l, pair<int, bool> r) {
	if (l.second) {
		// do or
		tree[i] = l.first | r.first;
		return make_pair (l.first | r.first, false);
	}
	else {
		// do xor
		tree[i] = l.first ^ r.first;
		return make_pair (l.first ^ r.first, true);
	}
}

pair<int, bool> build (int i, int l, int r) {
	if (l == r)
		return make_pair (tree[i] = arr[l], true);
	
	int mid = (l + r) / 2;
	return merge (i, build (left (i), l, mid), build (right (i), mid + 1, r));
}

pair<int, bool> update (int i, int l, int r, int idx, int v) {
	if (l == r)
		return make_pair (tree[i] = arr[idx] = v, true);
	
	int mid = (l + r) / 2;
	pair<int, bool> lv, rv;

	if (idx <= mid) {
		lv = update (left (i), l, mid, idx, v);
		rv = make_pair (tree[right (i)], lv.second);
	}
	else {
		rv = update (right (i), mid + 1, r, idx, v);
		lv = make_pair (tree[left (i)], rv.second);
	}

	return merge (i, lv, rv);
}

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);

	int N, M;
	cin >> N >> M;

	tree = vector<int> (4 * (1 << N));
	arr = vector<int> (1 << N);

	for (int n = 0; n < (1 << N); ++n)
		cin >> arr[n];
	
	build (0, 0, (1 << N) - 1);

	for (int m = 0; m < M; ++m) {
		int p, b;
		cin >> p >> b;

		update (0, 0, (1 << N) - 1, p - 1, b);
		cout << tree[0] << "\n";
	}
}