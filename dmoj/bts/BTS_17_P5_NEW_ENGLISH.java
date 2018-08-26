import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

// atharva washimkar
// Sep 11, 2017

public class BTS_17_P5_NEW_ENGLISH {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = in.iscan ();

		List<Request>[] list = new ArrayList[N];
		NavigableSet<Request>[] list2 = new TreeSet[26];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Request> ();

		for (int i = 0; i < 26; ++i)
			list2[i] = new TreeSet<Request> ();

		for (int m = 0; m < M; ++m) {
			char c = in.sscan ().charAt (0);
			int x = in.iscan (), i = in.iscan () - 1;

			list[i].add (new Request (c, x, i));
			list2[c - 'a'].add (new Request (c, x, i));
		}

		char[] ans = new char[N];
		Arrays.fill (ans, '~');

		boolean good = true;

		final int[] sum = new int[26];
		int[] last = new int[26];

		NavigableSet<Integer> idx = new TreeSet<Integer> ();
		for (int n = 0; n < N && good; ++n) {
			idx.add (n);

			for (Request r : list[n]) {
				if (sum[r.c - 'a'] > r.x) {
					good = false;
					break;
				}
				else {
					int s = n;
					int left = r.x - sum[r.c - 'a'];
					int e = last[r.c - 'a'];
					Integer nxt = e;

					while (!idx.isEmpty () && (nxt = idx.ceiling (nxt)) != null && nxt <= s && left > 0) {
						ans[nxt] = r.c;
						idx.remove (nxt);
						--left;
					}

					if (left != 0) {
						good = false;
						break;
					}

					last[r.c - 'a'] = n + 1;
					sum[r.c - 'a'] = r.x;
				}
			}
		}

		for (int i = 0; i < 26 && !idx.isEmpty (); ++i) {
			while (!idx.isEmpty ()) {
				Request nxt = list2[i].ceiling (new Request ((char) ('a' + i), 13, idx.last ()));

				if (nxt == null)
					ans[idx.pollLast ()] = (char) ('a' + i);
				else
					break;
			}
		}

		if (!idx.isEmpty ())
			good = false;

		if (good)
			out.println (new String (ans));
		else
			out.println (-1);

		out.close ();
	}

	private static class Request implements Comparable<Request> {

		char c;
		int x, i;

		public int compareTo (Request r) {
			return Integer.compare (i, r.i);
		}

		public boolean equals (Object o) {
			Request r = (Request) o;
			return c == r.c && x == r.x && i == r.i;
		}

		public int hashCode () {
			return (int) c * 17 + x * 19 + i * 31;
		}

		public Request (char c, int x, int i) {
			this.c = c;
			this.x = x;
			this.i = i;
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