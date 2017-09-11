#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

// given N elements in an array, perform Q update element/range sum queries
// use square root decomposition to split array into buckets and compute partial sums for each bucket
// when updating an element, simply update partial sum of the bucket in O(1)
// when querying range sum, iterate through the sqrt(N) buckets to sum the partial sums of the buckets in the given range
// also, remove any additional individual elements from the sum by iterating through the start and end buckets
// this is done in O(sqrt(N)) time

int N;
int arr[100000];
int ps[317];
int first[317];
int last[317];

int main () {
	cin.sync_with_stdio (0);
	cin.tie (0);
	
	cin >> N;
	
	for (int n = 0; n < N; ++n)
		cin >> arr[n];
	
	int rt = (int) ceil (sqrt (N));
	memset (first, 1 << 30, sizeof (first));
	memset (last, -1, sizeof (last));
	
	for (int n = 0; n < N; ++n) {
		ps[n / rt] += arr[n];
		first[n / rt] = min (first[n / rt], n);
		last[n / rt] = max (last[n / rt], n);
	}

	int Q;
	cin >> Q;
	
	for (int q = 0, c, a, b; q < Q; ++q) {
		cin >> c >> a >> b;
		
		if (c == 1) {
			// update arr[a] = b
			--a;
			int block = a / rt;
			ps[block] -= arr[a];
			ps[block] += b;
			arr[a] = b;
		}
		else if (c == 2) {
			// find sum from [a,b]
			--a; --b;
			
			int block1 = a / rt, block2 = b / rt;			
			long long sum = 0L;
			
			// sum all blocks in between - jump by sqrt(N)
			for (int i = block1 + 1; i < block2; ++i)
				sum += ps[i];
			
			// deal with partial block sums
			for (int i = a; i <= min (b, last[block1]); ++i)
				sum += arr[i];
			
			// avoid summing same thing twice
			if (block1 != block2)
				for (int i = max (a, first[block2]); i <= b; ++i)
					sum += arr[i];
				
			cout << sum << '\n';
		}
	}
}