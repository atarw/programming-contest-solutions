import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class SIMON_AND_MARCY {

	static int[][] cache;

	public static int solve (int M, int[] songs, int[] princesses, int curr) {
		if (cache[M][curr] == 0 && !(curr == 0 || M == 0)) {
			if (songs[curr] > M) {
				cache[M][curr] = solve (M, songs, princesses, curr - 1);
			}
			else {
				cache[M][curr] = Math.max (princesses[curr] + solve (M - songs[curr], songs, princesses, curr - 1),
				                           solve (M, songs, princesses, curr - 1));
			}
		}

		return cache[M][curr];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int C = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);
		cache = new int[M + 1][C + 1];

		int[] songs = new int[C];
		int[] princesses = new int[C];

		for (int c = 0; c < C; c++) {
			t = in.readLine ().split (" ");
			princesses[c] = Integer.parseInt (t[0]);
			songs[c] = Integer.parseInt (t[1]);
		}

		System.out.println (solve (M, songs, princesses, C - 1));
	}
}