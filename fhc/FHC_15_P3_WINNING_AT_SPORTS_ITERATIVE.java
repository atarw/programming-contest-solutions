import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class FHC_15_P3_WINNING_AT_SPORTS_ITERATIVE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int T = Integer.parseInt (in.readLine ());
		int MOD = 1000000007;

		for (int tt = 1; tt <= T; ++tt) {
			t = in.readLine ().split ("-");
			int A = Integer.parseInt (t[0]), B = Integer.parseInt (t[1]);

			long[][] dp = new long[A + 1][B + 1];

			for (int a = 1; a <= A; ++a) {
				dp[a][0] = 1L;

				for (int b = 1; b < Math.min (a, B + 1); ++b)
					dp[a][b] = (dp[a][b] + dp[a - 1][b] + dp[a][b - 1]) % MOD;
			}

			long free = dp[A][B];

			dp = new long[A + 1][B + 1];
			Arrays.fill (dp[0], 1L);

			for (int b = 1; b <= B; ++b)
				for (int a = 1; a <= b; ++a)
					dp[a][b] = (dp[a][b] + dp[a - 1][b] + dp[a][b - 1]) % MOD;

			for (int a = B + 1; a <= A; ++a)
				dp[a][B] = (dp[a][B] + dp[a - 1][B] + dp[a][Math.max (0, B - 1)]) % MOD;

			long full = dp[A][B];

			out.println ("Case #" + tt + ": " + free + " " + full);
		}

		out.close ();
	}
}