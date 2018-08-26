import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CCO_01_P1_THE_MONKEY_DANCE {

	static int[] next;
	static boolean[] vis;

	public static int cycle (int u) {
		int cyc = 0;

		while (true) {
			vis[u] = true;

			if (vis[next[u]])
				break;

			u = next[u];
			++cyc;
		}

		return cyc;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		while (true) {
			int N = Integer.parseInt (in.readLine ());

			if (N == 0)
				break;

			next = new int[N];
			vis = new boolean[N];

			for (int n = 0, a, b; n < N; ++n) {
				t = in.readLine ().split (" ");
				a = Integer.parseInt (t[0]) - 1;
				b = Integer.parseInt (t[1]) - 1;
				next[a] = b;
			}

			long lcm = 1;

			for (int n = 0; n < N; ++n)
				if (!vis[n])
					lcm = UTILITIES.lcm (lcm, 1 + cycle (n));

			out.println (lcm);
		}

		out.close ();
	}

	private static class UTILITIES {

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

		public static long gcd (long a, long b) {
			return b == 0 ? a : gcd (b, a % b);
		}

		public static long lcm (long a, long b) {
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