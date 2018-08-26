import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class UVA_10911_FORMING_QUIZ_TEAMS {

	static double[] cache;
	static int[] x, y;
	static int N;

	public static double dist (int s, int s2) {
		return Math.sqrt (Math.pow (x[s] - x[s2], 2) + Math.pow (y[s] - y[s2], 2));
	}

	public static boolean check (int num, int i) {
		return ((num >> i) & 1) == 1;
	}

	public static int clear (int num, int i) {
		return num & ~(1 << i);
	}

	public static double solve (int state) {
		if (cache[state] != Double.POSITIVE_INFINITY)
			return cache[state];

		if (state == 0)
			return cache[state] = 0;

		for (int n = 0; n < 2 * N; ++n)
			for (int n2 = n + 1; n2 < 2 * N; ++n2)
				if (check (state, n) && check (state, n2))
					cache[state] = Math.min (cache[state], dist (n, n2) + solve (clear (clear (state, n2), n)));

		return cache[state];
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		for (int c = 1; ; ++c) {
			N = Integer.parseInt (in.readLine ());

			if (N == 0)
				break;

			cache = new double[1 << (2 * N + 1)];
			x = new int[2 * N];
			y = new int[2 * N];

			for (int n = 0; n < 2 * N; ++n) {
				t = in.readLine ().split (" ");
				x[n] = Integer.parseInt (t[1]);
				y[n] = Integer.parseInt (t[2]);
			}

			Arrays.fill (cache, Double.POSITIVE_INFINITY);
			out.printf ("Case %d: %.2f\n", c, solve ((1 << (2 * N)) - 1));
		}

		out.close ();
	}
}