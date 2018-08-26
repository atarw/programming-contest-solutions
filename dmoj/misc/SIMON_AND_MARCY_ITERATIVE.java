import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class SIMON_AND_MARCY_ITERATIVE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int C = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);

		int[] p = new int[C], w = new int[C];

		for (int c = 0; c < C; ++c) {
			t = in.readLine ().split (" ");
			p[c] = Integer.parseInt (t[0]);
			w[c] = Integer.parseInt (t[1]);
		}

		int[][] dp = new int[C + 1][M + 1];

		// base case

		for (int c = 0; c <= C; ++c)
			dp[c][0] = 0;

		for (int m = w[C - 1]; m <= M; ++m)
			dp[C][m] = p[C - 1];

		// dp

		for (int c = C - 2; c >= 0; --c) {
			for (int m = 0; m <= M; ++m) {
				dp[c][m] = dp[c + 1][m];

				if (m >= w[c])
					dp[c][m] = Math.max (dp[c + 1][m - w[c]] + p[c], dp[c][m]);
			}
		}

		out.print (dp[0][M]);
		out.close ();
	}
}