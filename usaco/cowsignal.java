import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class cowsignal {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT ("cowsignal.in");
		PrintWriter out = new PrintWriter ("cowsignal.out");

		int M = in.iscan (), N = in.iscan (), K = in.iscan ();
		int[][] img = new int[M][N];

		for (int m = 0; m < M; ++m) {
			for (int n = 0; n < N; ++n)
				img[m][n] = in.cscan ();
			in.cscan ();
		}

		int[][] new_img = new int[K * M][K * N];

		for (int m = 0; m < M; ++m)
			for (int n = 0; n < N; ++n)
				for (int km = m * K; km < m * K + K; ++km)
					for (int kn = n * K; kn < n * K + K; ++kn)
						new_img[km][kn] = img[m][n];

		for (int[] arr : new_img) {
			for (int i : arr)
				out.print ((char) i);
			out.println ();
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