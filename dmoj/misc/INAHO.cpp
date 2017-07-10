#include <vector>
#include <stack>

using namespace std;

struct edge {
	int u, v, ru, rv, su, sv;
	
	edge (int u, int v, int ru, int rv, int su, int sv) {
		this->u = u; this->v = v;
		this->ru = ru; this->rv = rv;
		this->su = su; this->sv = sv;
	}
};

vector <int> parent, size;
stack <edge> st;

int find (int u) {
	if (parent [u] != u)
		return find (parent [u]);
	
	return parent [u];
}

void merge (int u, int v, int ru, int rv) {	
	if (ru != rv) {
		if (size [rv] > size [ru]) {
			parent [ru] = rv;
			size [rv] += size [ru];
		}
		else {
			parent [rv] = ru;
			size [ru] += size [rv];
		}
	}
}

void Init (int N) {
	parent = vector <int> (N);
	size = vector <int> (N, 1);
	
	for (int n = 0; n < N; ++n)
		parent [n] = n;
}

void AddEdge (int U, int V) {
	--U; --V;
	int ru = find (U), rv = find (V);
	edge nxt = edge (U, V, ru, rv, size [ru], size [rv]);
	merge (U, V, ru, rv);
	st.push (nxt);
}

void RemoveLastEdge () {
	edge last = st.top ();
	st.pop ();
	
	parent [last.ru] = last.ru;
	parent [last.rv] = last.rv;
	size [last.ru] = last.su;
	size [last.rv] = last.sv;
}

int GetSize (int U) {
	return size [find (U - 1)];
}