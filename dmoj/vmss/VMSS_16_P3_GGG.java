import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// atharva washimkar
// August 11, 2018

public class VMSS_16_P3_GGG {

	public static int binary_search_ceil (int[] arr, int from, int to, int val) {
		int mid;

		while (to - from > 1) {
			mid = from + ((to - from) >> 1);

			if (arr[mid] >= val)
				to = mid;
			else
				from = mid;
		}

		return to;
	}

	public static int LIS (List<Integer> list) {
		int[] dp = new int[list.size ()];
		dp[0] = list.get (0);
		int size = 1;

		for (int n = 1; n < list.size () && size < list.size (); ++n) {
			if (list.get (n) < dp[0])
				dp[0] = list.get (n);

			else if (list.get (n) > dp[size - 1]) {
				dp[size] = list.get (n);
				++size;
			}

			else
				dp[binary_search_ceil (dp, -1, size - 1, list.get (n))] = list.get (n);
		}

		return size;
	}

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		Map<Integer, Integer> map = new HashMap<Integer, Integer> (N, 1.1f);

		for (int n = 0; n < N; ++n)
			map.put (in.iscan (), n);

		int N2 = in.iscan ();
		List<Integer> list = new ArrayList<Integer> (Math.min (N, N2) >> 1);

		for (int n = 0, v; n < N2; ++n) {
			v = in.iscan ();

			if (map.containsKey (v))
				list.add (map.get (v));
		}

		out.print (list.isEmpty () ? 0 : LIS (list));
		out.close ();
	}

	private static class INPUT {

		private InputStream stream;
		private byte[] buf = new byte[8192];
		private int curChar, numChars;

		public INPUT (InputStream stream) {
			this.stream = stream;
		}

		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
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
				res = (res << 1) + (res << 3);
				res += c - '0';

				c = cscan ();
			}
			while (!space (c));

			return res * sgn;
		}

		public boolean space (int c) {
			return c == ' ' || c == '\n';
		}
	}
}