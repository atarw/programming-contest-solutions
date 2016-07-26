#include <stdio.h>
#include <string.h>
#include <vector>

#define scan(x) do{while((x=getchar_unlocked())<'0'); for(x-='0'; '0'<=(_=getchar_unlocked()); x=(x<<3)+(x<<1)+_-'0');}while(0)
#define max(a,b) ((a) < (b) ? (b) : (a))
#define INF 0x3f3f3f3f

char _;

using namespace std;

int cache [52][51][51][51];
int flakes [51][51][2];
int R, C, B, K, M, S, T;

inline int solve (int _time, int pos, int temp, int cap) {
	if (cache [_time][pos][temp][cap] || _time == T + 1 || cap == K || temp >= B) {
		return cache [_time][pos][temp][cap];
	}
	
	//has option to take
	if (flakes [pos][_time][1] != -1 && temp + flakes [pos][_time][0] < B && cap + 1 <= K) {
		for (int move = M; move >= 0; --move) {
			if (pos - move >= 0) {
				cache [_time][pos][temp][cap] = max (cache [_time][pos][temp][cap], flakes [pos][_time][1] + solve (_time + 1, pos - move, temp + flakes [pos][_time][0], cap + 1));
			}
			
			if (pos + move <= C) {
				cache [_time][pos][temp][cap] = max (cache [_time][pos][temp][cap], flakes [pos][_time][1] + solve (_time + 1, pos + move, temp + flakes [pos][_time][0], cap + 1));
			}
		}
	}
	
	//do not take at all
	for (int move = M; move >= 0; --move) {
		if (pos - move >= 0) {
			cache [_time][pos][temp][cap] = max (cache [_time][pos][temp][cap], solve (_time + 1, pos - move, temp, cap));
		}
		
		if (pos + move <= C) {
			cache [_time][pos][temp][cap] = max (cache [_time][pos][temp][cap], solve (_time + 1, pos + move, temp, cap));
		}
	}
		
	return cache [_time][pos][temp][cap];
}

int main () {
	scan (R); scan (C); scan (S); scan (B); scan (K); scan (M);
	
	int T_i, V_i, C_i, R_i;
	
	memset (cache, 0, sizeof (cache));
	memset (flakes, -1, sizeof (flakes));
	
	for (int s = 0; s < S; ++s) {
		scan (T_i); scan (V_i); scan (C_i); scan (R_i);
		
		flakes [C_i][R_i][0] = T_i; flakes [C_i][R_i][1] = V_i;
		T = max (T, R_i);
	}
	
	printf ("%d", solve (0, 1, 0, 0));
}