import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class CCC_15_S5_GREEDY_FOR_PIES {

	static int[] ln, bag;
	static int[][][][] cache;//current pos in ln, start pos in bag, end pos in bag, can take pie at curr pos
	static int N, M;

	public static int solve (int curr, int s, int e, int take) {
		if (curr == N && s >= e || cache[curr][s][e][take] != 0) {//dp/base case
			return cache[curr][s][e][take];
		}

		if (curr < N) {
			//skip pie in line
			cache[curr][s][e][take] = solve (curr + 1, s, e, 1);

			//take pie in line
			if (take == 1) {
				cache[curr][s][e][take] = Math.max (cache[curr][s][e][take], ln[curr] + solve (curr + 1, s, e, 0));
			}
		}

		//has pies in bag
		if (s < e) {
			if (take == 1) {//take pie from bag
				cache[curr][s][e][take] = Math.max (cache[curr][s][e][take], bag[e - 1] + solve (curr, s, e - 1, 0));
			}
			else {//insert pie in bag
				cache[curr][s][e][take] = Math.max (cache[curr][s][e][take], solve (curr, s + 1, e, 1));
			}
		}

		return cache[curr][s][e][take];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		N = Integer.parseInt (in.readLine ());
		ln = new int[N];

		for (int n = 0; n < N; ++n) {
			ln[n] = Integer.parseInt (in.readLine ());
		}

		M = Integer.parseInt (in.readLine ());
		bag = new int[M];

		for (int m = 0; m < M; ++m) {
			bag[m] = Integer.parseInt (in.readLine ());
		}

		Arrays.sort (bag);
		cache = new int[N + 1][M + 1][M + 1][2];

		System.out.print (solve (0, 0, M, 1));
	}
}