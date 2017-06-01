import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;

public class bphoto {

	static NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer> ();
	static int[] bit;

	public static int query (int pos) {
		int sum = 0;

		for (; pos > 0; pos -= pos & -pos)
			sum += bit[pos];

		return sum;
	}

	public static void update (int pos, int val) {
		for (; pos < bit.length; pos += pos & -pos)
			bit[pos] += val;
	}

	public static void main (String[] t) throws IOException {
		/*INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);*/
		INPUT in = new INPUT ("bphoto.in");
		PrintWriter out = new PrintWriter (new File ("bphoto.out"));

		int N = in.iscan ();
		int[] arr = new int[N], arr2 = new int[N];

		for (int n = 0; n < N; ++n)
			arr[n] = arr2[n] = in.iscan ();

		Arrays.sort (arr2);

		for (int n = 0; n < N; ++n)
			if (!map.containsKey (arr2[n]))
				map.put (arr2[n], map.size () + 1);

		int[] L = new int[N];
		int[] R = new int[N];

		bit = new int[N + 1];

		for (int n = 0; n < N; ++n) {
			update (map.get (arr[n]), 1);
			L[n] = n - query (map.get (arr[n])) + 1;
		}

		bit = new int[N + 1];

		for (int n = N - 1; n >= 0; --n) {
			update (map.get (arr[n]), 1);
			R[n] = N - n - query (map.get (arr[n]));
		}

		int count = 0;

		for (int n = 0; n < N; ++n)
			if (Math.max (L[n], R[n]) > Math.min (L[n], R[n]) * 2)
				++count;

		out.println (count);
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