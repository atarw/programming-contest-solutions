import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class VMSS_15_P5_JEFFREY_AND_FRANK_AND_A_LACK_OF_ROADS {

	static Apple[] A;
	static int[][][] cache;

	public static int solve (int R, int S, int curr) {
		if (cache[R][S][curr] == 0 && !(curr == 0 || R < 0 || S < 0)) {
			if (A[curr - 1].B > S || A[curr - 1].A > R) {
				cache[R][S][curr] = solve (R, S, curr - 1);
			}
			else {
				cache[R][S][curr] = Math.max (A[curr - 1].V + solve (R - A[curr - 1].A, S - A[curr - 1].B, curr),
				                              solve (R, S, curr - 1));
			}
		}

		return cache[R][S][curr];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), R = Integer.parseInt (t[1]), S = Integer.parseInt (t[2]);

		A = new Apple[N];
		cache = new int[R + 1][S + 1][N + 1];

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			A[n] = new Apple (t[0], Integer.parseInt (t[1]), Integer.parseInt (t[2]), Integer.parseInt (t[3]));
		}

		System.out.println (solve (R, S, N));

		int r = R, n = N, s = S;
		int[] bought = new int[N];

		for (; n > 0; ) {
			if (r - A[n - 1].A < 0 || s - A[n - 1].B < 0 || cache[r][s][n] - cache[r - A[n - 1].A][s - A[n - 1].B][n]
					!= A[n - 1].V) {
				n--;
			}
			else {
				bought[n - 1]++;
				r -= A[n - 1].A;
				s -= A[n - 1].B;
			}
		}

		for (int a = 0; a < A.length; a++) {
			System.out.println (A[a].N + " " + bought[a]);
		}
	}

	private static class Apple {

		String N;
		int V, A, B;

		public Apple (String N, int V, int A, int B) {
			this.N = N;
			this.V = V;
			this.A = A;
			this.B = B;
		}
	}
}