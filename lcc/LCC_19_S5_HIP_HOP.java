import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// Nov 23, 2019

public class LCC_19_S5_HIP_HOP {

	public static long[][][][] betweenblocks (int[][] blockpoints, int[] h) {
		long[][][][] dp = new long[blockpoints.length][4][blockpoints.length][4];

		// can make this more efficient but whatever
		for (int n = 0; n < blockpoints.length; ++n) {
			for (int i = 0; i < blockpoints[0].length; ++i) {
				int s = blockpoints[n][i];
				long[] dp2 = new long[h.length];

				// only need forward
				if (s + 1 < h.length)
					dp2[s + 1] = Math.abs (h[s + 1] - h[s]);

				for (int j = s + 2; j < h.length; ++j)
					dp2[j] = Math.min (dp2[j - 2] + Math.abs (h[j] - h[j - 2]),
					                   dp2[j - 1] + Math.abs (h[j] - h[j - 1]));

				// copy to dp array
				for (int j = n; j < blockpoints.length; ++j)
					for (int k = (j == n ? i : 0); k < blockpoints[0].length; ++k)
						dp[n][i][j][k] = dp[j][k][n][i] = dp2[blockpoints[j][k]];
			}
		}

		return dp;
	}

	public static long[] inblock (int[] h, int n, int sqrtn) {
		long[] dp2 = new long[sqrtn];
		int s = n % sqrtn;

		// forward
		if (s + 1 < sqrtn && n + 1 < h.length)
			dp2[s + 1] = Math.abs (h[n + 1] - h[n]);

		for (int j = 2; s + j < sqrtn && n + j < h.length; ++j)
			dp2[s + j] = Math.min (dp2[s + j - 1] + Math.abs (h[n + j] - h[n + j - 1]),
			                       dp2[s + j - 2] + Math.abs (h[n + j] - h[n + j - 2]));

		// backward
		if (s - 1 >= 0 && n - 1 >= 0)
			dp2[s - 1] = Math.abs (h[n] - h[n - 1]);

		for (int j = 2; s - j >= 0 && n - j >= 0; ++j)
			dp2[s - j] = Math.min (dp2[s - j + 1] + Math.abs (h[n - j] - h[n - j + 1]),
			                       dp2[s - j + 2] + Math.abs (h[n - j] - h[n - j + 2]));

		return dp2;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), Q = in.iscan ();
		int[] h = new int[N];

		for (int n = 0; n < N; ++n)
			h[n] = in.iscan ();

		int sqrtn = (int) Math.sqrt (N) + 1;
		// first two indices of block, last two indices of block
		int[][] blockpoints = new int[sqrtn][4];

		// calculate first two indices of each block
		for (int n = N - 1; n >= 0; --n) {
			blockpoints[n / sqrtn][1] = blockpoints[n / sqrtn][0];
			blockpoints[n / sqrtn][0] = n;
		}

		// calculate last two indices of each block
		for (int n = 0; n < N; ++n) {
			blockpoints[n / sqrtn][2] = blockpoints[n / sqrtn][3];
			blockpoints[n / sqrtn][3] = n;
		}

		// some adjusting
		for (int n = sqrtn; n < N; ++n) {
			if (blockpoints[n / sqrtn][2] == 0)
				blockpoints[n / sqrtn][2] = blockpoints[n / sqrtn][3];

			if (blockpoints[n / sqrtn][1] == 0)
				blockpoints[n / sqrtn][1] = blockpoints[n / sqrtn][0];
		}

		// betweenblocks[n][i][m][j] = optimal path from blockpoints[n][i] to blockpoints[m][j]
		long[][][][] betweenblocks = betweenblocks (blockpoints, h);

		// inblock[n][i][j] = optimal path from blockpoints[n][i] to h[n + j] (inside same block)
		long[][][] inblock = new long[blockpoints.length][blockpoints[0].length][];

		for (int n = 0; n < blockpoints.length; ++n)
			for (int i = 0; i < blockpoints[0].length; ++i)
				inblock[n][i] = inblock (h, blockpoints[n][i], sqrtn);

		for (int q = 0; q < Q; ++q) {
			int x = in.iscan () - 1, y = in.iscan () - 1;
			int l = Math.min (x, y), r = Math.max (x, y);

			int block1 = l / sqrtn;
			int block2 = r / sqrtn;

			long ans;

			if (block1 != block2) {
				int s = l % sqrtn, e = r % sqrtn;

				// s to s-end1 to f-start1 to f
				long op1 = inblock[block1][2][s] + betweenblocks[block1][2][block2][0] + inblock[block2][0][e];
				// s to s-end1 to f-start2 to f
				long op2 = inblock[block1][2][s] + betweenblocks[block1][2][block2][1] + inblock[block2][1][e];
				// s to s-end2 to f-start1 to f
				long op3 = inblock[block1][3][s] + betweenblocks[block1][3][block2][0] + inblock[block2][0][e];
				// s to s-end2 to f-start2 to f
				long op4 = inblock[block1][3][s] + betweenblocks[block1][3][block2][1] + inblock[block2][1][e];

				ans = Math.min (op1, Math.min (op2, Math.min (op3, op4)));
			}
			else {
				// do dp from l to r
				long[] dp2 = inblock (h, l, sqrtn);
				ans = dp2[r % sqrtn];
			}

			out.println (ans);
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

		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
			}

			if (numChars == -1)
				return numChars;

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;

			while (space (c))
				c = cscan ();

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

			while (space (c))
				c = cscan ();

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

			while (space (c))
				c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * UTILITIES.fast_pow (10, iscan ());

				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E')
						return res * UTILITIES.fast_pow (10, iscan ());

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;

			while (space (c))
				c = cscan ();

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