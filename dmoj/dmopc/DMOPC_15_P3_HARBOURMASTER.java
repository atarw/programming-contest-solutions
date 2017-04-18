import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DMOPC_15_P3_HARBOURMASTER {

	static int[] req = new int[3];
	static int[] C, S, P;

	public static double solve (int n, int k, int c, int s, int p) {//k = ppl left

		if (c > req[0]) {
			c = req[0];
		}

		if (s > req[1]) {
			s = req[1];
		}

		if (p > req[2]) {
			p = req[2];
		}

		if (k == 0 || n == 0) {
			return Math.min ((c * 1.0) / req[0], Math.min ((s * 1.0) / req[1], (p * 1.0) / req[2]));
		}
		else {
			return Math.max (solve (n - 1, k, c, s, p), solve (n - 1, k - 1, c + C[n - 1], s + S[n - 1], p + P[n -
					1]));
		}
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		for (int i = 0; i < 3; i++) {
			req[i] = Integer.parseInt (t[i]);
		}

		int N = Integer.parseInt (in.readLine ());
		C = new int[N];
		S = new int[N];
		P = new int[N];

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			C[n] = Integer.parseInt (t[0]);
			S[n] = Integer.parseInt (t[1]);
			P[n] = Integer.parseInt (t[2]);
		}

		System.out.printf ("%.1f", solve (N, 5, 0, 0, 0) * 100);
	}
}