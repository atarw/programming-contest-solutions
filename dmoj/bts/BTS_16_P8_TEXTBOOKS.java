import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

// atharva washimkar
// August 11, 2018

public class BTS_16_P8_TEXTBOOKS {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int[] s = new int[N], e = new int[N], w = new int[N];

		long used = 0L;

		NavigableSet<Integer> unique = new TreeSet<Integer> ();

		for (int n = 0; n < N; ++n) {
			s[n] = in.iscan () - 1;
			int l = in.iscan ();
			e[n] = s[n] + l;
			w[n] = in.iscan ();
			unique.add (s[n]);
			unique.add (e[n]);
			used += (long) (w[n]) * (long) (l);
		}

		Map<Integer, Integer> cc = new HashMap<Integer, Integer> ();

		for (int p : unique) {
			cc.put (p, cc.size ());
		}

		long[] arr = new long[cc.size ()];

		for (int n = 0; n < N; ++n) {
			long max = -1;

			for (int b = cc.get (s[n]); b < cc.get (e[n]); ++b) {
				max = Math.max (max, arr[b]);
			}

			for (int a = cc.get (s[n]); a < cc.get (e[n]); ++a) {
				arr[a] = w[n] + max;
			}
		}

		long shade = 0L;

		int f = unique.first ();
		int n = 1;

		for (int p : unique) {
			if (p == f) continue;

			shade += arr[n - 1] * (p - f);
			f = p;
			++n;
		}

		out.print ((shade - used) % 1000000007L);
		out.close ();
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);

			return b * fast_pow (b * b, x / 2);
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

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}