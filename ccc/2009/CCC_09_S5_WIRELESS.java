import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CCC_09_S5_WIRELESS {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int M = in.iscan (), N = in.iscan (), K = in.iscan ();
		int xc, yc, r, b;
		int v1, v2;

		int[][] diff = new int[M + 1][N + 1];

		for (int k = 0; k < K; ++k) {
			xc = in.iscan () - 1;
			yc = in.iscan () - 1;
			r = in.iscan ();
			b = in.iscan ();

			//(x - xc)^2 + (y - yc)^2 = r^2, where xc and yc is the center
			//x = xc +- sqrt (r^2 - (y - yc)^2), positive solution -> right side, negative solution -> left side

			for (int y = Math.max (0, yc - r); y <= Math.min (M, yc + r); ++y) {
				v1 = (int) Math.floor (xc + Math.sqrt (r * r - (y - yc) * (y - yc))) + 1;
				v2 = (int) Math.ceil (xc - Math.sqrt (r * r - (y - yc) * (y - yc)));

				diff[y][Math.min (N, v1)] -= b;
				diff[y][Math.max (0, v2)] += b;
			}
		}

		int curr = 0;
		int max = 0, shops = 0;

		for (int m = 0; m < M; m++) {
			curr = 0;

			for (int n = 0; n < N; n++) {
				curr += diff[m][n];

				if (curr > max) {
					shops = 1;
					max = curr;
				}
				else if (curr == max) {
					++shops;
				}
			}
		}

		out.println (max);
		out.println (shops);
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

				if (numChars <= 0) return -1;
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

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}