import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DMOPC_13_P5_A_ROMANTIC_DINNER {

	static int[] V, T, F;
	static int[][][] cache;

	public static int best (int M, int U, int curr) {
		if (cache[M][U][curr] == 0 && !(M == 0 || U == 0 || curr == 0)) {
			if (T[curr - 1] > M || F[curr - 1] > U) {
				cache[M][U][curr] = best (M, U, curr - 1);
			}
			else {
				cache[M][U][curr] = Math.max (V[curr - 1] + best (M - T[curr - 1], U - F[curr - 1], curr - 1), best
						(M, U, curr - 1));
			}
		}

		return cache[M][U][curr];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int M = Integer.parseInt (t[0]), U = Integer.parseInt (t[1]), R = Integer.parseInt (t[2]);

		V = new int[R];
		T = new int[R];
		F = new int[R];
		cache = new int[M + 1][U + 1][R + 1];

		for (int r = 0; r < R; r++) {
			t = in.readLine ().split (" ");
			V[r] = Integer.parseInt (t[0]);
			T[r] = Integer.parseInt (t[1]);
			F[r] = Integer.parseInt (t[2]);
		}

		System.out.print (best (M, U, R));
	}
}