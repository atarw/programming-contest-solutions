import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CCC_15_J5_PI_DAY_ITERATIVE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		int K = Integer.parseInt (in.readLine ());
		int[][] dp = new int[N + 1][K + 1];

		for (int n = 0; n <= K; ++n)
			dp[n][1] = dp[n][n] = 1;

		for (int n = 1; n <= N; ++n)
			for (int k = 1; k < Math.min (n, K + 1); ++k)
				dp[n][k] = dp[n - 1][k - 1] + dp[n - k][k];

		out.println (dp[N][K]);
		out.close ();
	}
}