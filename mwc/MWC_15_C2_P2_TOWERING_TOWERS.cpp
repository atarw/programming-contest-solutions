#include <stdio.h>
#include <stack>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
char _;

using namespace std;

int main()
{
	int N;
	scan (N);
	int height [N];

	for (int n = 0; n < N; ++n) {
		scan (height [n]);
	}

	stack <int> st;
	st.push (0);
	
	putchar_unlocked ('0'); putchar_unlocked (' ');

   for (int i = 1; i < N; ++i)
   {
	  while (!st.empty () && height [st.top ()] <= height [i]) {
		st.pop ();
	}
		
	printf ("%d", st.empty () ? i : i - st.top ()); putchar_unlocked (' ');
	st.push (i);
   }
}