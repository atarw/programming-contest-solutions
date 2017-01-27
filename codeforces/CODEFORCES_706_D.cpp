#include <stdio.h>
#include <string.h>

using namespace std;

int trie [6000000][2], cnt [6000000][2];
int insert = 1;

int bit (int a, int k) {
	return (a >> k) & 1;
}

void ins (int a) {
	int curr = 1, pos = 0;
	
	for (int i = 30; i >= 0; --i) {
		pos = bit (a, i);
		
		if (cnt [curr][pos] == 0) {
			trie [curr][pos] = ++insert;
		}
		
		++cnt [curr][pos];			
		curr = trie [curr][pos];
	}
}

void del (int a) {
	int curr = 1, pos = 0;
	
	for (int i = 30; i >= 0; --i) {
		pos = bit (a, i);
		--cnt [curr][pos];
		curr = trie [curr][pos];
	}
}

int best_xor (int a) {
	int curr = 1, pos = 0, val = 0;
	
	for (int i = 30; i >= 0; --i) {
		pos = bit (a, i) ^ 1;
		
		if (cnt [curr][pos] == 0) { // cannot take best path
			pos ^= 1;
		}
		
		val ^= ((-pos) ^ val) & (1 << i);
		curr = trie [curr][pos];
	}
	
	return a ^ val;
}

int main () {
	int Q, x;
	char chr;
	
	scanf ("%d", &Q);
		
	memset (trie, 0, sizeof (trie));
	memset (cnt, 0, sizeof (cnt));
	
	ins (0);
	
	while (Q--) {
		scanf (" %c %d", &chr, &x);
		
		if (chr == '+')
			ins (x);

		else if (chr == '-')
			del (x);

		else
			printf ("%d\n", best_xor (x));
	}

	return 0;
}