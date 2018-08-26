import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_931_C {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int[] arr = new int[N];

		Map<Integer, Integer> map = new HashMap<Integer, Integer> ();

		for (int n = 0; n < N; ++n) {
			arr[n] = in.iscan ();

			if (!map.containsKey (arr[n]))
				map.put (arr[n], 1);
			else
				map.put (arr[n], map.get (arr[n]) + 1);
		}

		List<Integer> keys = new ArrayList<Integer> (map.keySet ());
		Collections.sort (keys);

		if (keys.size () == 1 || keys.size () == 2 && keys.get (0) + 1 == keys.get (1)) {
			// do nothing, just use original array
		}
		else if (keys.size () == 2) {
			int key1 = keys.get (0), key2 = keys.get (1);
			int min = 0, max = 0;

			if (map.get (key1) > map.get (key2)) {
				min = key2;
				max = key1;
			}
			else {
				min = key1;
				max = key2;
			}

			map.put ((min + max) / 2, 2 * map.get (min));
			map.put (max, map.get (max) - map.get (min));
			map.remove (min);
		}
		else {
			// try replacing two middle elements with one high one low, or vice versa
			int key1 = keys.get (0), key2 = keys.get (1), key3 = keys.get (2);
			int middleRemoved = map.get (key2) - (map.get (key2) % 2);
			int hiloRemoved = 2 * Math.min (map.get (key1), map.get (key3));

			if (middleRemoved > hiloRemoved) {
				map.put (key1, map.get (key1) + middleRemoved / 2);
				map.put (key3, map.get (key3) + middleRemoved / 2);
				map.put (key2, map.get (key2) - middleRemoved);
			}
			else {
				map.put (key2, map.get (key2) + hiloRemoved);
				map.put (key1, map.get (key1) - hiloRemoved / 2);
				map.put (key3, map.get (key3) - hiloRemoved / 2);
			}
		}

		for (int i : keys)
			if (map.containsKey (i) && map.get (i) == 0)
				map.remove (i);

		List<Integer> ans = new ArrayList<Integer> ();

		for (int i : map.keySet ())
			for (int j = 0; j < map.get (i); ++j)
				ans.add (i);

		int measurements = 0;

		for (int n = 0; n < N; ++n) {
			if (map.containsKey (arr[n])) {
				++measurements;

				if (map.get (arr[n]) == 1)
					map.remove (arr[n]);
				else
					map.put (arr[n], map.get (arr[n]) - 1);
			}
		}

		out.println (measurements);

		for (int i : ans)
			out.print (i + " ");

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

		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
			}

			if (numChars == -1)
				return numChars;

			return buf[curChar++];
		}

		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;

			while (space (c))
				c = cscan ();

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

		public String sscan () throws IOException {
			int c = cscan ();

			while (space (c))
				c = cscan ();

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

			while (space (c))
				c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			double res = 0;

			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E')
					return res * UTILITIES.fast_pow (10, iscan ());

				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E')
						return res * UTILITIES.fast_pow (10, iscan ());

					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}

			return res * sgn;
		}

		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;

			while (space (c))
				c = cscan ();

			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}

			long res = 0;

			do {
				res = (res << 1) + (res << 3);
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

	public static class UTILITIES {

		static final double EPS = 10e-6;

		public static int lower_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] >= x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int upper_bound (int[] arr, int x) {
			int low = 0, high = arr.length, mid = -1;

			while (low < high) {
				mid = (low + high) / 2;

				if (arr[mid] > x)
					high = mid;
				else
					low = mid + 1;
			}

			return low;
		}

		public static int gcd (int a, int b) {
			return b == 0 ? a : gcd (b, a % b);
		}

		public static int lcm (int a, int b) {
			return a * b / gcd (a, b);
		}

		public static int fast_pow_mod (int b, int x, int mod) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;

			return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);

			return b * fast_pow (b * b, x / 2);
		}

		public static long choose (long n, long k) {
			k = Math.min (k, n - k);
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = val * (n - i) / (i + 1);

			return val;
		}

		public static long permute (int n, int k) {
			if (n < k) return 0;
			long val = 1;

			for (int i = 0; i < k; ++i)
				val = (val * (n - i));

			return val;
		}
	}
}