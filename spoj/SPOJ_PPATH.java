import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class SPOJ_PPATH {

	static boolean[] sieve = new boolean[10000];

	public static List <Integer> next (int n) {
		List <Integer> next = new ArrayList <Integer> ();
		char[] arr = new char[4];

		int tmp = n;

		for (int pos = 3; pos >= 0 && tmp > 0; --pos) {
			arr[pos] = Character.forDigit (tmp % 10, 10);
			tmp /= 10;
		}

		char ctmp;
		int p = 0;

		for (int i = 0; i < 4; ++i) {
			ctmp = arr[i];

			for (int d = 0; d < 10; ++d) {
				arr[i] = Character.forDigit (d, 10);
				p = Character.getNumericValue (arr[3]) + Character.getNumericValue (arr[2]) * 10 + Character
						.getNumericValue (arr[1]) * 100 + Character.getNumericValue (arr[0]) * 1000;

				if (p != n && p >= 1000 && sieve[p])
					next.add (p);
			}

			arr[i] = ctmp;
		}

		return next;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		Arrays.fill (sieve, true);
		sieve[0] = sieve[1] = false;

		for (int i = 2; i < sieve.length; ++i) {
			if (sieve[i]) {
				for (int j = 2; i * j < sieve.length; ++j) {
					sieve[i * j] = false;
				}
			}
		}

		int T = in.iscan ();

		while (T-- > 0) {
			int x = in.iscan (), y = in.iscan ();
			int[] cache = new int[10000];
			Arrays.fill (cache, Integer.MAX_VALUE);
			cache[x] = 0;

			Queue <Integer> q = new ArrayDeque <Integer> ();
			List <Integer> next;
			q.offer (x);

			while (!q.isEmpty ()) {
				int curr = q.poll ();
				next = next (curr);

				for (int i = 0; i < next.size (); ++i) {
					if (cache[next.get (i)] > cache[curr] + 1) {
						cache[next.get (i)] = cache[curr] + 1;
						q.offer (next.get (i));
					}
				}
			}

			out.println (cache[y]);
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