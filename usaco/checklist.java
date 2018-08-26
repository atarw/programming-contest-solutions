import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class checklist {

	static int H, G;
	static int[][][] dp;
	static int[] hx, hy;
	static int[] gx, gy;

	public static int solve (int x, int y, int b, int h, int g) {
		//System.out.println (x + " " + y + " " + h + " " + g);
		if (dp[h][g][b] != -1)
			return dp[h][g][b];

		if (h == H && g == G)
			return dp[h][g][b] = 0;

		int h_dist = h == H ? -1 : (x - hx[h + 1]) * (x - hx[h + 1]) + (y - hy[h + 1]) * (y - hy[h + 1]);
		int g_dist = g == G ? -1 : (x - gx[g + 1]) * (x - gx[g + 1]) + (y - gy[g + 1]) * (y - gy[g + 1]);

		if (g == G)
			return dp[h][g][b] = h_dist + solve (hx[h + 1], hy[h + 1], 1, h + 1, g);

		if (h == H - 1)
			return dp[h][g][b] = g_dist + solve (gx[g + 1], gy[g + 1], 0, h, g + 1);

		return dp[h][g][b] = Math.min (h_dist + solve (hx[h + 1], hy[h + 1], 1, h + 1, g), g_dist + solve (gx[g + 1],
		                                                                                                   gy[g + 1],
		                                                                                                   0, h, g +
				                                                                                                   1));
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT ("checklist.in");
		PrintWriter out = new PrintWriter ("checklist.out");

		H = in.iscan ();
		G = in.iscan ();
		hx = new int[H + 1];
		hy = new int[H + 1];
		gx = new int[G + 1];
		gy = new int[G + 1];

		for (int h = 0; h < H; ++h) {
			hx[h + 1] = in.iscan ();
			hy[h + 1] = in.iscan ();
		}

		for (int g = 0; g < G; ++g) {
			gx[g + 1] = in.iscan ();
			gy[g + 1] = in.iscan ();
		}

		dp = new int[H + 1][G + 1][2];

		for (int i = 0; i < dp.length; ++i)
			for (int j = 0; j < dp[0].length; ++j)
				for (int k = 0; k < dp[0][0].length; ++k)
					dp[i][j][k] = -1;

		int ans = solve (hx[1], hy[1], 1, 1, 0);
		System.out.println (ans);

		out.println (ans);
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