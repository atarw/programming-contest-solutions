import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class BFS_17_P4_PAIR_PROGRAMMING {

	static Line[] ln;
	static List<Integer>[] list;
	static final int AC = 0, WA = 1, WTF = 2;
	static int[][] dp, dp2;

	public static int min (int n, int l) {
		if (ln[n].ref != -1) { // has parent
			if (dp[n][l] != -1)
				return dp[n][l];

			dp[n][l] = 0;

			if (ln[n].status != WTF) { // determined status
				boolean mistake = true;

				if (ln[n].status != l)
					++dp[n][l];
				else
					mistake = false;

				for (int v : list[n])
					dp[n][l] += min (v, mistake ? WA : AC);
			}
			else { // undetermined
				// num of ways if var is set to WA or AC
				int op1 = 0, op2 = 0;
				boolean mistake1 = true, mistake2 = true;

				if (l == WA) {
					++op2;
					mistake1 = false;
				}
				else {
					++op1;
					mistake2 = false;
				}

				for (int v : list[n]) {
					op1 += min (v, mistake1 ? WA : AC);
					op2 += min (v, mistake2 ? WA : AC);
				}

				dp[n][l] = Math.min (op1, op2);
			}
		}
		else { // first node
			dp[n][l] = 0;

			if (ln[n].status != WTF) { // determined status
				if (ln[n].status == WA)
					++dp[n][l];

				for (int v : list[n])
					dp[n][l] += min (v, ln[n].status);
			}
			else { // undetermined
				int op1 = 1, op2 = 0;

				for (int v : list[n]) {
					op1 += min (v, WA);
					op2 += min (v, AC);
				}

				dp[n][l] = Math.min (op1, op2);
			}
		}
		return dp[n][l];
	}

	public static int max (int n, int l) {
		if (ln[n].ref != -1) { // has parent
			if (dp2[n][l] != -1)
				return dp2[n][l];

			dp2[n][l] = 0;

			if (ln[n].status != WTF) { // determined status
				boolean mistake = true;

				if (ln[n].status != l)
					++dp2[n][l];
				else
					mistake = false;

				for (int v : list[n])
					dp2[n][l] += max (v, mistake ? WA : AC);
			}
			else { // undetermined
				int op1 = 0, op2 = 0;
				boolean mistake1 = true, mistake2 = true;

				if (l == WA) {
					++op2;
					mistake1 = false;
				}
				else {
					++op1;
					mistake2 = false;
				}

				for (int v : list[n]) {
					op1 += max (v, mistake1 ? WA : AC);
					op2 += max (v, mistake2 ? WA : AC);
				}

				dp2[n][l] = Math.max (op1, op2);
			}
		}
		else { // first node
			dp2[n][l] = 0;

			if (ln[n].status != WTF) { // determined status
				if (ln[n].status == WA)
					++dp2[n][l];

				for (int v : list[n])
					dp2[n][l] += max (v, ln[n].status);
			}
			else { // undetermined
				int op1 = 1, op2 = 0;

				for (int v : list[n]) {
					op1 += max (v, WA);
					op2 += max (v, AC);
				}

				dp2[n][l] = Math.max (op1, op2);
			}
		}
		return dp2[n][l];
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		ln = new Line[N];
		list = new ArrayList[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Integer> ();

		for (int n = 0, status, ref; n < N; ++n) {
			char c = in.sscan ().charAt (0);

			if (c == 'C')
				ref = -1;
			else
				ref = in.iscan () - 1;

			String s = in.sscan ();

			if (s.equals ("AC"))
				status = AC;
			else if (s.equals ("WA"))
				status = WA;
			else
				status = WTF;

			ln[n] = new Line (n, status, ref);

			if (ref != -1)
				list[ref].add (n);
		}

		dp = new int[N][2];
		dp2 = new int[N][2];

		for (int n = 0; n < N; ++n) {
			dp[n][0] = dp[n][1] = -1;
			dp2[n][0] = dp2[n][1] = -1;
		}

		int min = 0, max = 0;

		for (int n = 0; n < N; ++n) {
			if (ln[n].ref == -1) {
				min += min (n, 0);
				max += max (n, 0);
			}
		}

		out.print (min + " " + max);
		out.close ();
	}

	private static class Line {

		int status;
		int ref;
		int n;

		public Line (int n, int status, int ref) {
			this.n = n;
			this.status = status;
			this.ref = ref;
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