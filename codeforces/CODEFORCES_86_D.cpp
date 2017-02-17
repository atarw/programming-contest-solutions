#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <string.h>
#include <vector>

#define scan(x) do{while((x=getchar())<'0'); for(x-='0'; '0'<=(_=getchar()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

struct query {
	int l, r, i;
	
	query () {}
	
	query (int l, int r, int i) {
		this->l = l; this->r = r; this->i = i;
	}
};

int blocks = 447;
int cnt [1000001], arr [200001];
long long int ans [200001];
vector <query> qq;
long long int curr_ans = 0;

bool comp (query const& q1, query const& q2) {
	if (q1.l / blocks == q2.l / blocks)
		return q1.r > q2.r;
	return q1.l / blocks > q2.l / blocks;
}
	
void add (int v) {
	curr_ans -= (long long int) cnt [v] * cnt [v] * v;
	++cnt [v];
	curr_ans += (long long int) cnt [v] * cnt [v] * v;
}
	
void remove (int v) {
	curr_ans -= (long long int) cnt [v] * cnt [v] * v;
	--cnt [v];
	curr_ans += (long long int) cnt [v] * cnt [v] * v;
}

int main () {
	int N, Q;
	scan (N); scan (Q);
	
	for (int n = 0; n < N; ++n)
		scan (arr [n]);
	
	for (int n = 0; n < 1000001; ++n)
		cnt [n] = 0;
	
	qq = vector <query> (Q);
	
	for (int q = 0, l, r; q < Q; ++q) {
		scan (l); scan (r); --l; --r;
		qq [q] = query (l, r, q);
	}
	
	sort (qq.begin (), qq.end (), &comp);
	int currL = 0, currR = 0;
	
	for (int q = 0; q < Q; ++q) {
		// remove old data
		while (currL < qq [q].l) {
			remove (arr [currL]);
			++currL;
		}
		
		// add current data
		while (currL > qq [q].l) {
			add (arr [currL - 1]);
			--currL;
		}
		
		while (currR <= qq [q].r) {
			add (arr [currR]);
			++currR;
		}
		
		// remove old data
		while (currR > 1 + qq [q].r) {
			remove (arr [currR - 1]);
			--currR;
		}
		
		ans [qq [q].i] = curr_ans;
	}
	
	for (int q = 0; q < Q; ++q)
		cout << ans [q] << "\n";
	
	return 0;
}