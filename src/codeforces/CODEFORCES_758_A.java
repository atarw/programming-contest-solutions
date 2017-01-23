import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author atharva
 */
public class CODEFORCES_758_A {
	public static void main (String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		INPUT in = new INPUT (inputStream);
		PrintWriter out = new PrintWriter (outputStream);
		TaskA solver = new TaskA ();
		solver.solve (1, in, out);
		out.close ();
	}

	static class TaskA {
		public void solve (int testNumber, INPUT in, PrintWriter out) {
			int N = in.iscan ();
			int[] arr = new int[N];

			for (int n = 0; n < N; ++n)
				arr[n] = in.iscan ();

			Arrays.sort (arr);

			int total = 0;

			for (int n = 0; n < N; ++n)
				total += arr[N - 1] - arr[n];

			out.print (total);
		}

	}

	static class INPUT {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public int cscan () {
			//if (numChars == -1) throw new InputMismatchException ();
			try {
				if (curChar >= numChars) {
					curChar = 0;
					numChars = stream.read (buf);

					//if (numChars <= 0) return -1;
				}
			} catch (IOException e) {
				throw new RuntimeException (e);
			}

			return buf[curChar++];
		}

		public int iscan () {
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

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

	}
}

