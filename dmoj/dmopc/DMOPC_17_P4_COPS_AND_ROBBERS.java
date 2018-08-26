import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// atharva washimkar
// Feb 21, 2018

public class DMOPC_17_P4_COPS_AND_ROBBERS {

	public static void main (String[] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int[] arr = new int[N];

		for (int n = 0; n < N; ++n)
			arr[n] = in.iscan ();

		Map<Integer, Integer> frq = new HashMap<Integer, Integer> (N, 1.1f);

		for (int n = 0; n < N; ++n) {
			if (!frq.containsKey (arr[n])) {
				frq.put (arr[n], 1);
			}
			else {
				frq.put (arr[n], frq.get (arr[n]) + 1);
			}
		}

		if (frq.size () == 1) {
			out.print (-1);
		}
		else {
			Queue<Integer> free = new ArrayDeque<Integer> (N);
			PriorityQueue<Pair> pq = new PriorityQueue<Pair> ();
			PriorityQueue<Pair> pq2 = new PriorityQueue<Pair> ();

			for (int n = 1; n <= N; ++n)
				if (!frq.containsKey (n))
					free.offer (n);

			for (int i : frq.keySet ())
				pq.offer (new Pair (frq.get (i), i));

			for (int n = 0; n < N; ++n) {
				Pair rob = null;

				while (!pq.isEmpty ()) {
					if (pq.peek ().v == arr[n])
						pq2.offer (pq.poll ());
					else {
						rob = pq.poll ();
						break;
					}
				}

				if (rob == null) {
					while (!pq2.isEmpty ()) {
						if (pq2.peek ().v == arr[n])
							pq.offer (pq2.poll ());
						else {
							rob = pq2.poll ();
							break;
						}
					}
				}

				if (rob == null) {
					out.print (free.poll () + " ");
				}
				else {
					out.print (rob.v + " ");
				}
			}
		}

		out.close ();
	}

	private static class Pair implements Comparable<Pair> {

		int frq, v;

		@Override
		public String toString () {
			return String.format ("(frq: %d, v: %d)", frq, v);
		}

		@Override
		public int compareTo (Pair p) {
			if (Integer.compare (frq, p.frq) == 0)
				return Integer.compare (v, p.v);

			return Integer.compare (p.frq, frq);
		}

		@Override
		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass () != o.getClass ()) return false;

			Pair pair = (Pair) o;

			if (frq != pair.frq) return false;
			return v == pair.v;
		}

		@Override
		public int hashCode () {
			int result = frq;
			result = 31 * result + v;
			return result;
		}

		public Pair (int frq, int v) {
			this.frq = frq;
			this.v = v;
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
				if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());
				res *= 10;
				res += c - '0';
				c = cscan ();
			}

			if (c == '.') {
				c = cscan ();
				double m = 1;

				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());

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