import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CCC_05_S5_PINBALL_RANKING {

	static long inversions = 0;
	static int[] arr;

	public static void merge_sort (int s, int e) {
		if (s < e) {
			int mid = (s + e) / 2;

			merge_sort (s, mid);
			merge_sort (mid + 1, e);

			merge (s, mid, e);
		}
	}

	public static void merge (int s, int m, int e) {
		int[] tmp_arr = new int[e - s + 1];

		int s2 = s;
		int m2 = m + 1;
		int index = 0;

		while (s2 <= m && m2 <= e) {
			if (arr[s2] <= arr[m2]) {
				tmp_arr[index] = arr[s2];
				index++;
				s2++;
			}
			else {
				tmp_arr[index] = arr[m2];
				index++;
				m2++;
				inversions = inversions + m + 1 - s2;
			}
		}

		while (s2 <= m) {
			tmp_arr[index] = arr[s2];
			index++;
			s2++;
		}

		while (m2 <= e) {
			tmp_arr[index] = arr[m2];
			index++;
			m2++;
		}

		for (int i = s; i <= e; i++) {
			arr[i] = tmp_arr[i - s];
		}
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int T = in.iscan ();
		arr = new int[T];

		for (int i = 0; i < T; i++) {
			arr[i] = in.iscan ();
		}

		merge_sort (0, T - 1);

		DecimalFormat form = new DecimalFormat ("0.00");
		form.setRoundingMode (RoundingMode.FLOOR);
		out.print (form.format (1.0 * inversions / T + 1));
		out.close ();
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
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
				res *= 10;
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
				if (c == 'e' || c == 'E') return res * Math.pow (10, iscan ());
				//if (c < '0' || c > '9') throw new InputMismatchException ();

				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * Math.pow (10, iscan ());
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

				res *= 10;
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