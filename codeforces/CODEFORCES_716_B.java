import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class CODEFORCES_716_B {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		String s = in.sscan ();

		if (s.length () < 26)
			out.print (-1);
		else {
			int[][] arr = new int[27][s.length () + 1];

			for (int n = 0; n < s.length (); ++n)
				if (s.charAt (n) != '?')
					++arr[s.charAt (n) - 'A'][n + 1];
				else
					++arr[26][n + 1];

			for (int n = 0; n < arr.length; ++n)
				for (int n2 = 1; n2 < arr[n].length; ++n2)
					arr[n][n2] += arr[n][n2 - 1];

			boolean good = true, poss = false;
			int cnt = 0;

			int x = 0;

			for (int n = 0; n + 26 <= s.length (); ++n) {
				good = true;
				cnt = 0;

				for (int c = 0; c < 26; ++c) {
					if (arr[c][n + 26] - arr[c][n] > 1) {
						good = false;
						break;
					}
					else if (arr[c][n + 26] - arr[c][n] == 0) {
						++cnt;
					}
				}

				if (good) {
					if (cnt == arr[26][n + 26] - arr[26][n]) {
						x = n;
						poss = true;
						break;
					}
				}
			}

			if (poss) {
				Queue <Character> q = new ArrayDeque <Character> ();

				for (int c = 0; c < 26; ++c)
					if (arr[c][x + 26] - arr[c][x] == 0)
						q.offer ((char) ('A' + c));

				for (int n = 0; n < s.length (); ++n) {
					if (x <= n && n < x + 26) {
						if (s.charAt (n) == '?') {
							out.print (q.poll ());
						}
						else {
							out.print (s.charAt (n));
						}
					}
					else {
						if (s.charAt (n) == '?') {
							out.print ('A');
						}
						else {
							out.print (s.charAt (n));
						}
					}
				}
			}
			else {
				out.print (-1);
			}
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