import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MEC_16_P3_GETTING_GOOD_AT_PROGRAMMING {

	static int[][] profit, weight;
	static int[][][] cache;//time + skill + level -> max

	public static int solve (int T, int S, int L) {
		//System.out.println (T + " " + S + " " + L);

		if (cache[T][S][L] == 0 && !(T == 0 || S == profit.length || L == profit[S].length)) {
			if (T < weight[S][L]) {
				cache[T][S][L] = solve (T, S + 1, 0);
			}
			else {
				cache[T][S][L] = Math.max (profit[S][L] + solve (T - weight[S][L], (L + 1) % profit[S].length == (L +
						1) ? S : S + 1, (L + 1) % profit[S].length), solve (T, S + 1, 0));
			}
		}

		return cache[T][S][L];
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), T = Integer.parseInt (t[1]), L;

		profit = new int[N][];
		weight = new int[N][];
		cache = new int[T + 1][N + 1][];

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			L = Integer.parseInt (t[0]);

			profit[n] = new int[L];
			weight[n] = new int[L];

			for (int i = 0; i <= T; i++) {
				cache[i][n] = new int[L + 1];
				cache[i][N] = new int[L + 1];//really inefficient...
			}

			for (int l = 0; l < L; l++) {
				profit[n][l] = Integer.parseInt (t[l * 2 + 2]);
				weight[n][l] = Integer.parseInt (t[l * 2 + 1]);
			}
		}

		System.out.println (solve (T, 0, 0));
	}
}