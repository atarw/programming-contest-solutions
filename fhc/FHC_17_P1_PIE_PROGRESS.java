import java.io.*;
import java.util.Arrays;

public class FHC_17_P1_PIE_PROGRESS {

	static int N, M;
	static int[][] pies, pref, dp;

	public static int solve (int n, int p) {
		if (dp[n][p] != -1)
			return dp[n][p];

		if (n == N || p >= N - n)
			return dp[n][p] = 0;

		dp[n][p] = p != 0 ? solve (n + 1, p - 1) : 1 << 30;

		for (int m = 1; m <= M && p + m - 1 <= N; ++m)
			dp[n][p] = Math.min (dp[n][p], pref[n][m] + m * m + solve (n + 1, p + m - 1));

		return dp[n][p];
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT ("pie_progress2.txt");
		PrintWriter out = new PrintWriter (new File ("pie_progress2.out"));

		int T = in.iscan ();

		for (int tt = 1; tt <= T; ++tt) {
			N = in.iscan ();
			M = in.iscan ();
			pies = new int[N][M];
			pref = new int[N][M + 1];
			dp = new int[N + 1][N + 1];

			for (int n = 0; n < N; ++n) {
				for (int m = 0; m < M; ++m)
					pies[n][m] = in.iscan ();

				Arrays.sort (pies[n]);

				for (int m = 1; m <= M; ++m)
					pref[n][m] = pref[n][m - 1] + pies[n][m - 1];
			}

			for (int n = 0; n < dp.length; ++n)
				Arrays.fill (dp[n], -1);

			out.println ("Case #" + tt + ": " + solve (0, 0));
		}

		out.close ();
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public INPUT (String file) throws IOException {
			this.stream = new FileInputStream (file);
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);

			return b * fast_pow (b * b, x / 2);
		}

		public int cscan () throws IOException {
			//if (numChars == -1) throw new InputMismatchException ();

			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);

				//if (numChars <= 0) return -1;
			}

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			int res = 0;

			do {
				//if (c < '0' || c > '9') throw new InputMismatchException ();

				res = (res << 1) + (res << 3);
				//res *= 10;
				res += c - '0';

				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		public String sscan () throws IOException {
			int c = cscan ();
			while (space (c)) c = cscan ();

			StringBuilder res = new StringBuilder ();

			do {
				res.appendCodePoint (c);
				c = cscan ();
			}
			while (!space (c));

			return res.toString ();
		}

		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E') return res * fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
				//if (c < '0' || c > '9') throw new InputMismatchException ();

				//res = (res << 1) + (res << 3);
				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
					//if (c < '0' || c > '9') throw new InputMismatchException ();

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			long res = 0;

			do {
				//if (c < '0' || c > '9') throw new InputMismatchException();

				res = (res << 1) + (res << 3);
				//res *= 10;
				res += c - '0';
				c = cscan ();

			}
			while (!space (c));

			return res * sgn;
		}

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}