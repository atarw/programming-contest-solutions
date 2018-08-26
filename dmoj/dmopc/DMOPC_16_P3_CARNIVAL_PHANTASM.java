import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DMOPC_16_P3_CARNIVAL_PHANTASM {

	static int[] dist;

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), S = Integer.parseInt (t[1]);
		NavigableSet<Integer>[] k_stall = new TreeSet[101];
		dist = new int[N + 1];

		t = in.readLine ().split (" ");

		for (int n = 1; n <= N; ++n)
			dist[n] = Integer.parseInt (t[n - 1]);

		for (int k = 0; k < k_stall.length; ++k)
			k_stall[k] = new TreeSet<Integer> (new Comparator<Integer> () {
				public int compare (Integer x, Integer y) {
					if (Integer.compare (dist[x], dist[y]) == 0)
						return Integer.compare (x, y);
					return Integer.compare (dist[x], dist[y]);
				}
			});

		for (int s = 0, ss, a; s < S; ++s) {
			t = in.readLine ().split (" ");
			ss = Integer.parseInt (t[0]);
			a = Integer.parseInt (t[1]);
			k_stall[a].add (ss);
		}

		int Q = Integer.parseInt (in.readLine ());
		char cmd;

		for (int q = 0, x = 0, k; q < Q; ++q) {
			t = in.readLine ().split (" ");

			cmd = t[0].charAt (0);

			if (cmd == 'A') {
				x = Integer.parseInt (t[1]);
				k = Integer.parseInt (t[2]);
				k_stall[k].add (x);
			}
			else if (cmd == 'S') {
				x = Integer.parseInt (t[1]);
				k = Integer.parseInt (t[2]);
				k_stall[k].remove (x);
			}
			else if (cmd == 'E') {
				x = Integer.parseInt (t[1]);
				k = Integer.parseInt (t[2]);
				for (int kk = 0; kk < k_stall.length; ++kk)
					k_stall[kk].remove (x);

				dist[x] = k;
			}
			else if (cmd == 'Q') {
				k = Integer.parseInt (t[1]);
				if (k_stall[k].isEmpty ())
					out.println (-1);
				else
					out.println (k_stall[k].first ());
			}
		}

		out.close ();
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