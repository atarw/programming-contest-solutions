import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class CCC_18_S3_ROBOTHIEVES {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();
		char[][] grid = new char[N][M];

		int sx = -1, sy = -1;

		for (int n = 0; n < N; ++n) {
			String ln = in.sscan ();

			for (int m = 0; m < M; ++m) {
				grid[n][m] = ln.charAt (m);

				if (grid[n][m] == 'S') {
					sx = n; sy = m;
				}
			}
		}

		boolean[][] bad = new boolean[N][M];

		for (int n = 0; n < N; ++n) {
			for (int m = 0; m < M; ++m) {
				if (grid[n][m] == 'C') {
					for (int i = n; i >= 0 && grid[i][m] != 'W'; --i)
						if (grid[i][m] == '.' || grid[i][m] == 'C' || grid[i][m] == 'S')
							bad[i][m] = true;

					for (int i = n; i < N && grid[i][m] != 'W'; ++i)
						if (grid[i][m] == '.' || grid[i][m] == 'C' || grid[i][m] == 'S')
							bad[i][m] = true;

					for (int i = m; i < M && grid[n][i] != 'W'; ++i)
						if (grid[n][i] == '.' || grid[n][i] == 'C' || grid[n][i] == 'S')
							bad[n][i] = true;

					for (int i = m; i >= 0 && grid[n][i] != 'W'; --i)
						if (grid[n][i] == '.' || grid[n][i] == 'C' || grid[n][i] == 'S')
							bad[n][i] = true;
				}
			}
		}

		int[][] dp = new int[N][M];

		for (int[] arr : dp)
			Arrays.fill (arr, 1 << 25);

		dp[sx][sy] = 0;

		Queue<State> q = new ArrayDeque<State> ();

		if (!bad[sx][sy])
			q.offer (new State (sx, sy));

		int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		char[] allowed = {'D', 'U', 'R', 'L'};

		while (!q.isEmpty ()) {
			State cur = q.poll ();

			for (int d = 0; d < dir.length; ++d) {
				int newx = cur.x + dir[d][0];
				int newy = cur.y + dir[d][1];

				if (0 <= newx && newx < N && 0 <= newy && newy < M && grid[newx][newy] != 'W' && !bad[newx][newy]) {
					if (grid[cur.x][cur.y] == '.' || grid[cur.x][cur.y] == 'S' || grid[cur.x][cur.y] == allowed[d]) {
						if (dp[cur.x][cur.y] + (grid[cur.x][cur.y] == allowed[d] ? 0 : 1) < dp[newx][newy]) {
							dp[newx][newy] = dp[cur.x][cur.y] + (grid[cur.x][cur.y] == allowed[d] ? 0 : 1);
							q.offer (new State (newx, newy));
						}
					}
				}
			}
		}

		for (int n = 0; n < N; ++n)
			for (int m = 0; m < M; ++m)
				if (grid[n][m] == '.')
					out.println (dp[n][m] == (1 << 25) ? -1 : dp[n][m]);

		out.close ();
	}

	private static class State {

		int x, y;

		public State (int x, int y) {
			this.x = x; this.y = y;
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
				res = (res << 1) + (res << 3);
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
				if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());
				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());

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
				res = (res << 1) + (res << 3);
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

	public static class UTILITIES {

		static final double EPS = 10e-6;

		public static int lower_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] >= x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int upper_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] > x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int gcd (int a, int b) {
			return b == 0 ? a : gcd (b, a % b);
		}

		public static int lcm (int a, int b) {
			return a * b / gcd (a, b);
		}

		public static int fast_pow_mod (int b, int x, int mod) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;

			return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);

			return b * fast_pow (b * b, x / 2);
		}

		public static long choose (long n, long k) {
			k = Math.min (k, n - k);
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = val * (n - i) / (i + 1);

			return val;
		}

		public static long permute (int n, int k) {
			if (n < k) return 0;
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = (val * (n - i));

			return val;
		}
	}
}