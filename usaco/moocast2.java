import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class moocast2 {

	static int[] parent;

	public static int find (int u) {
		if (parent[u] != u)
			parent[u] = find (parent[u]);
		return parent[u];
	}

	public static boolean union (int a, int b) {
		int r1 = find (a), r2 = find (b);

		if (r1 == r2)
			return false;

		parent[r1] = r2;
		find (a);
		find (b);
		return true;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT ("moocast.in");
		PrintWriter out = new PrintWriter ("moocast.out");

		int N = in.iscan ();
		int[] x = new int[N];
		int[] y = new int[N];

		for (int n = 0; n < N; ++n) {
			x[n] = in.iscan ();
			y[n] = in.iscan ();
		}

		Edge[] edges = new Edge[N * (N - 1) / 2];

		int i = 0;

		for (int n = 0; n < N; ++n)
			for (int n2 = n + 1; n2 < N; ++n2)
				edges[i++] = new Edge (n, n2, (x[n] - x[n2]) * (x[n] - x[n2]) + (y[n] - y[n2]) * (y[n] - y[n2]));

		//System.out.println (i);
		Arrays.sort (edges);
		parent = new int[N];

		for (int n = 0; n < N; ++n)
			parent[n] = n;

		//for (Edge e : edges)
		//System.out.println (e.s + " " + e.e + " " + e.w);

		int max = 0;

		for (Edge e : edges)
			if (union (e.s, e.e))
				max = e.w;

		out.println (max);
		out.close ();
	}

	private static class Edge implements Comparable<Edge> {

		int s, e, w;

		public Edge (int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		public int compareTo (Edge e) {
			return this.w - e.w;
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