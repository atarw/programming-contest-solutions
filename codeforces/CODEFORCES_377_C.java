import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author atharva
 */
public class CODEFORCES_377_C {
	public static void main (String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		INPUT in = new INPUT (inputStream);
		PrintWriter out = new PrintWriter (outputStream);
		TaskC solver = new TaskC ();
		solver.solve (1, in, out);
		out.close ();
	}

	static class TaskC {
		static int N;
		static int M;
		static int K;
		static int S;
		static char[][] maze;
		static int marked;

		public void solve (int testNumber, INPUT in, PrintWriter out) {
			N = in.iscan (); M = in.iscan (); K = in.iscan ();
			maze = new char[N][M];
			marked = 0; S = 0;

			String ln;

			for (int n = 0; n < N; ++n) {
				ln = in.sscan ();

				for (int m = 0; m < M; ++m) {
					maze[n][m] = ln.charAt (m);
					if (maze[n][m] == '.')
						++S;
				}
			}

			out:
			for (int n = 0; n < N; ++n)
				for (int m = 0; m < M; ++m)
					if (maze[n][m] == '.') {
						dfs (n, m);
						break out;
					}

			for (int n = 0; n < N; ++n) {
				for (int m = 0; m < M; ++m) {
					if (maze[n][m] == 'L')
						out.print ('.');
					else if (maze[n][m] == '#')
						out.print ('#');
					else
						out.print ('X');
				}
				out.println ();
			}
		}

		public static void dfs (int n, int m) {
			if (n < 0 || n >= N || m < 0 || m >= M || maze[n][m] != '.')
				return;

			if (marked != S - K) {
				maze[n][m] = 'L';
				++marked;
			}

			if (marked != S - K)
				dfs (n + 1, m);

			if (marked != S - K)
				dfs (n - 1, m);

			if (marked != S - K)
				dfs (n, m - 1);

			if (marked != S - K)
				dfs (n, m + 1);
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

		public String sscan () {
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

		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

	}
}

