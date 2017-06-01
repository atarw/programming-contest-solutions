import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DMOPC_16_P2_LINES {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();

		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>> ();
		int[] M = new int[N];
		int[] B = new int[N];

		boolean inf = false;

		for (int n = 0; n < N; ++n) {
			M[n] = in.iscan ();
			B[n] = in.iscan ();

			if (!map.containsKey (M[n])) {
				map.put (M[n], new HashSet<Integer> ());
				map.get (M[n]).add (B[n]);
			}
			else if (!map.get (M[n]).add (B[n])) {
				inf = true;
				break;
			}
		}

		long poi = 0;

		for (int n = 0; n < N; ++n) {
			poi = poi + N - map.get (M[n]).size ();
		}

		out.print (inf ? "Infinity" : poi / 2L);
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