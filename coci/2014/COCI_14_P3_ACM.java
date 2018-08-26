import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class COCI_14_P3_ACM {

	static int[][] cache;
	static int[][] est;
	static int N, s = 0;

	static int[][] seq = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};

	public static int solve (int n, int t) {
		if (cache[n][t] == 0) {
			if (n == N) {
				if (t != 2) {
					cache[n][t] = Integer.MAX_VALUE / 2;
				}
			}
			else {
				if (t == 2) {
					cache[n][t] = est[seq[s][t]][n] + solve (n + 1, t);
				}
				else {
					cache[n][t] = Math.min (est[seq[s][t]][n] + solve (n + 1, t), est[seq[s][t + 1]][n] + solve (n +
							                                                                                             1, t + 1));
				}
			}
		}

		return cache[n][t];
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		cache = new int[N + 1][3];
		est = new int[3][N];

		for (int i = 0; i < 3; ++i) {
			for (int n = 0; n < N; ++n) {
				est[i][n] = in.iscan ();
			}
		}

		int minDiff = Integer.MAX_VALUE;

		for (s = 0; s < seq.length; s++) {
			minDiff = Math.min (minDiff, est[seq[s][0]][0] + solve (1, 0));
			cache = new int[N + 1][3];
		}

		out.print (minDiff);
		out.close ();
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
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
				res *= 10;
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