import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;

// atharva washimkar
// August 11, 2018

public class hps {

	static int N, K;
	static int[] moves;
	static int[][][] dp;

	public static int win (int cow, int fj) {
		return (cow == 0 && fj == 2 || cow == 1 && fj == 0 || cow == 2 && fj == 1) ? 1 : 0;
	}

	public static int solve (int n, int k, int m) {
		if (dp[n][k][m] != -1)
			return dp[n][k][m];

		if (n == N)
			return dp[n][k][m] = 0;

		if (k == 0) {
			dp[n][k][m] = 0;

			for (int n2 = n; n2 < N; ++n2)
				dp[n][k][m] += win (m, moves[n2]);

			return dp[n][k][m];
		}

		dp[n][k][m] = win (m, moves[n]) + solve (n + 1, k, m);

		for (int mm = 0; mm < 3; ++mm)
			if (mm != m)
				dp[n][k][m] = Math.max (dp[n][k][m], win (m, moves[n]) + solve (n + 1, k - 1, mm));

		return dp[n][k][m];
	}

	public static void main (String[] t) throws IOException {
		/*INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);*/
		PrintWriter out2 = new PrintWriter (new File ("hps.in"));
		out2.println (100000 + " " + 20);

		Random r = new Random ();

		for (int n = 0, ra = r.nextInt (3); n < 100000; ++n, ra = r.nextInt (3))
			out2.println (ra == 0 ? "H" : ra == 1 ? "P" : "S");
		out2.close ();

		INPUT in = new INPUT ("hps.in");
		PrintWriter out = new PrintWriter (new File ("hps.out"));

		N = in.iscan ();
		K = in.iscan ();
		moves = new int[N];

		String ln;

		for (int n = 0; n < N; ++n) {
			ln = in.sscan ();
			moves[n] = (ln.charAt (0) == 'H' ? 0 : ln.charAt (0) == 'P' ? 1 : 2);
		}

		dp = new int[N + 1][K + 1][3];

		for (int n = 0; n < dp.length; ++n)
			for (int k = 0; k < dp[0].length; ++k)
				for (int m = 0; m < dp[0][0].length; ++m)
					dp[n][k][m] = -1;

		out.println (Math.max (Math.max (solve (0, K, 0), solve (0, K, 1)), solve (0, K, 2)));
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