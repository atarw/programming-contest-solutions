import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author atharva
 */
public class CODEFORCES_522_A {

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

		static Map<String, Integer> map;
		static List<Integer>[] list;
		static int[] dp;

		public static int dfs (int u) {
			if (dp[u] != -1)
				return dp[u];

			dp[u] = 0;

			for (int v : list[u])
				dp[u] = Math.max (dp[u], 1 + dfs (v));

			return dp[u];
		}

		public void solve (int testNumber, INPUT in, PrintWriter out) {
			int N = in.iscan ();
			map = new HashMap<String, Integer> ();
			list = new ArrayList[N << 1];

			for (int n = 0; n < list.length; ++n)
				list[n] = new ArrayList<Integer> ();

			for (int n = 0; n < N; ++n) {
				String a = in.sscan ().toLowerCase ();
				in.sscan ();
				String b = in.sscan ().toLowerCase ();

				if (!map.containsKey (a))
					map.put (a, map.size ());

				if (!map.containsKey (b))
					map.put (b, map.size ());

				list[map.get (b)].add (map.get (a));
			}

			dp = new int[map.size () + 1];
			Arrays.fill (dp, -1);
			out.print (dfs (map.get ("polycarp")) + 1);
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
			}
			catch (IOException e) {
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

