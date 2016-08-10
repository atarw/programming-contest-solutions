#include <stdio.h>
#include <string.h>
#include <vector>

//getchar_unlocked () may need to be replaced with getchar () depending on the c++ compiler
#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a) < (b) ? (b) : (a))
#define INF 0x3f3f3f3f

char _;

using namespace std;

int main () {
	//code here

	return 0;
}