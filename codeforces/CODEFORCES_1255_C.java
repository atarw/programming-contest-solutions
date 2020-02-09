import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// atharva washimkar
// Nov 28, 2019

public class CODEFORCES_1255_C {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		List<Integer>[] list = new ArrayList[N];
		int[] type = new int[N];

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<> ();

		for (int n = 0; n < N - 2; ++n) {
			int q1 = in.iscan () - 1, q2 = in.iscan () - 1, q3 = in.iscan () - 1;
			list[q1].add (q2);
			list[q1].add (q3);

			list[q2].add (q1);
			list[q2].add (q3);

			list[q3].add (q1);
			list[q3].add (q2);
		}

		int cur = 0;

		for (int n = 0; n < N; ++n) {
			if (list[n].size () == 2) {
				// endpoint
				cur = n;
				type[n] = 1;
			}
			else if (list[n].size () == 6) {
				// in the middle somewhere
				type[n] = 2;
			}
			else {
				// next to endpoint
				type[n] = 3;
			}
		}

		boolean[] vis = new boolean[N];

		while (true) {
			vis[cur] = true;
			out.print ((cur + 1) + " ");

			boolean found = false;

			if (type[cur] == 1) {
				// endpoint
				for (int nxt : list[cur]) {
					if (!vis[nxt] && type[nxt] == 3) {
						found = true;
						cur = nxt;
						break;
					}
				}
			}
			else if (type[cur] == 2) {
				// in the middle - up to two middles
				int nxt_type3 = -1;
				Map<Integer, Integer> middles = new HashMap<> ();

				for (int nxt : list[cur]) {
					if (!vis[nxt]) {
						if (type[nxt] == 2) {
							if (!middles.containsKey (nxt))
								middles.put (nxt, 1);
							else
								middles.put (nxt, middles.get (nxt) + 1);
						}
						else if (type[nxt] == 3) {
							nxt_type3 = nxt;
						}
					}
				}

				if (middles.isEmpty ()) {
					cur = nxt_type3;
					found = true;
				}
				else {
					int bestfrq = -1;

					for (int i : middles.keySet ()) {
						if (middles.get (i) > bestfrq) {
							bestfrq = middles.get (i);
							cur = i;
						}
					}

					found = true;
				}
			}
			else {
				// next to endpoint
				// two possible middle points - the adjacent one appears twice in the list
				Map<Integer, Integer> middles = new HashMap<> ();

				for (int nxt : list[cur]) {
					if (!vis[nxt]) {
						if (type[nxt] == 1) {
							found = true;
							cur = nxt;
							break;
						}
						else if (type[nxt] == 2) {
							if (!middles.containsKey (nxt))
								middles.put (nxt, 1);
							else
								middles.put (nxt, middles.get (nxt) + 1);
						}
					}
				}

				if (!found) {
					int bestfrq = -1;

					for (int i : middles.keySet ()) {
						if (middles.get (i) > bestfrq) {
							bestfrq = middles.get (i);
							cur = i;
						}
					}

					found = true;
				}
			}

			if (!found)
				break;
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