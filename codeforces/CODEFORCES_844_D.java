import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// atharva washimkar
// Aug 24, 2017

public class CODEFORCES_844_D {

	public static Node query (INPUT in, PrintWriter out, int cur) throws IOException {
		out.println ("? " + cur);
		out.flush ();
		return new Node (in.iscan (), in.iscan ());
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int first = in.iscan ();
		int x = in.iscan ();

		List<Integer> ord = new ArrayList<Integer> ();
		Node[] nodes = new Node[N + 1];
		nodes[first] = query (in, out, first);

		for (int n = 1; n <= N; ++n)
			if (n != first)
				ord.add (n);

		Collections.shuffle (ord);

		int minv = 1 << 30, min = 0;
		int maxv = -1, max = 0;

		if (nodes[first].v >= x) {
			minv = nodes[first].v;
			min = first;
		}
		else {
			maxv = nodes[first].v;
			max = first;
		}

		// query for 1000 (or all) nodes randomly, find the node closest to the x value, and iterate up from there
		for (int i = 0; i < Math.min (1000, ord.size ()); ++i) {
			nodes[ord.get (i)] = query (in, out, ord.get (i));

			if (nodes[ord.get (i)].v >= x) {
				if (nodes[ord.get (i)].v < minv) {
					minv = nodes[ord.get (i)].v;
					min = ord.get (i);
				}
			}
			else {
				if (nodes[ord.get (i)].v > maxv) {
					maxv = nodes[ord.get (i)].v;
					max = ord.get (i);
				}
			}
		}

		int ans = -1;

		if (minv == x) {
			ans = min;
		}
		else if (nodes[first].v >= x) {
			ans = first;
		}
		else {

			int cur = max;

			while (nodes[cur].nxt != -1) {
				if (nodes[nodes[cur].nxt] == null)
					nodes[nodes[cur].nxt] = query (in, out, nodes[cur].nxt);

				// rip
				if (nodes[nodes[cur].nxt].v == -1)
					return;

				cur = nodes[cur].nxt;

				if (nodes[cur].v >= x)
					break;
			}

			ans = cur;
		}

		if (ans != -1) {
			ans = nodes[ans].v;

			if (ans < x)
				ans = -1;
		}

		out.println ("! " + ans);
		out.flush ();
		out.close ();
	}

	private static class Node {

		int v, nxt;

		public Node (int v, int nxt) {
			this.v = v;
			this.nxt = nxt;
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