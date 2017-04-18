import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FHC_17_P3_MANIC_MOVING {

	static int[] parent;

	public static int find (int u) {
		if (parent[u] != u)
			parent[u] = find (parent[u]);

		return parent[u];
	}

	public static void union (int u, int v) {
		int r1 = find (u), r2 = find (v);

		if (r1 != r2)
			parent[r1] = r2;

		find (u);
		find (v);
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT ("manic_moving2.txt");
		PrintWriter out = new PrintWriter ("manic_moving2.out");

		int T = in.iscan ();

		for (int tt = 1; tt <= T; ++tt) {
			int N = in.iscan (), M = in.iscan (), K = in.iscan ();
			long[][] matrix = new long[N][N], dist = new long[N][N];
			int[] S = new int[K], D = new int[K];
			parent = new int[N];
			long[][][] dp = new long[N][K + 1][3];

			for (int n = 0; n < N; ++n) {
				Arrays.fill (dist[n], 1L << 50);
				dist[n][n] = 0;
			}

			for (int m = 0, a, b, g; m < M; ++m) {
				a = in.iscan () - 1;
				b = in.iscan () - 1;
				g = in.iscan ();
				union (a, b);

				if (matrix[a][b] == 0 || matrix[a][b] > g)
					matrix[a][b] = matrix[b][a] = dist[a][b] = dist[b][a] = g;
			}

			for (int k = 0; k < K; ++k) {
				S[k] = in.iscan () - 1;
				D[k] = in.iscan () - 1;
			}

			for (int k = 0; k < N; ++k)
				for (int i = 0; i < N; ++i)
					for (int j = 0; j < N; ++j)
						dist[i][j] = Math.min (dist[i][j], dist[i][k] + dist[k][j]);

			for (int n = 0; n < dp.length; ++n)
				for (int k = 0; k < dp[0].length; ++k)
					for (int c = 0; c < dp[0][0].length; ++c)
						dp[n][k][c] = 1L << 50;

			State curr = new State (0, 0, 0);
			dp[0][0][0] = 0;

			Queue <State> q = new ArrayDeque <State> ();
			q.offer (curr);

			long ans = 1L << 50;

			while (!q.isEmpty ()) {
				curr = q.poll ();

				if (curr.k == K) {
					if (curr.c == 1 && find (curr.n) == find (D[curr.k - curr.c])) {
						dp[curr.n][curr.k][curr.c] += dist[curr.n][D[curr.k - curr.c]];
						ans = Math.min (ans, dp[curr.n][curr.k][curr.c]);
					}

					if (curr.c == 2 && find (curr.n) == find (D[curr.k - curr.c]) && find (D[curr.k - curr.c]) == find
							(D[curr.k - curr.c + 1])) {
						dp[curr.n][curr.k][curr.c] += dist[curr.n][D[curr.k - curr.c]] + dist[D[curr.k - curr
								.c]][D[curr.k - curr.c + 1]];
						ans = Math.min (ans, dp[curr.n][curr.k][curr.c]);
					}

					if (curr.c == 0) {
						ans = Math.min (ans, dp[curr.n][curr.k][curr.c]);
					}
				}
				else {
					if (curr.c != 2) // can load
						if (find (curr.n) == find (S[curr.k]) && dp[S[curr.k]][curr.k + 1][curr.c + 1] > dp[curr
								.n][curr.k][curr.c] + dist[curr.n][S[curr.k]]) {
							dp[S[curr.k]][curr.k + 1][curr.c + 1] = dp[curr.n][curr.k][curr.c] + dist[curr.n][S[curr
									.k]];
							q.offer (new State (S[curr.k], curr.k + 1, curr.c + 1));
						}

					if (curr.c != 0)  // can deliver
						if (find (curr.n) == find (D[curr.k - curr.c]) && dp[D[curr.k - curr.c]][curr.k][curr.c - 1] >
								dp[curr.n][curr.k][curr.c] + dist[curr.n][D[curr.k - curr.c]]) {
							dp[D[curr.k - curr.c]][curr.k][curr.c - 1] = dp[curr.n][curr.k][curr.c] + dist[curr
									.n][D[curr.k - curr.c]];
							q.offer (new State (D[curr.k - curr.c], curr.k, curr.c - 1));
						}
				}
			}

			out.println ("Case #" + tt + ": " + (ans == 1L << 50 ? -1 : ans));
		}

		out.close ();
	}

	private static class State {

		int n, k, c;

		public State (int n, int k, int c) {
			this.n = n;
			this.k = k;
			this.c = c;
		}
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