import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class PALINDRONE {

	static final int MOD = 1000000000;

	public static long fast_pow_mod (long b, int x) {
		if (x == 0) return 1;
		if (x == 1) return b;
		if (x % 2 == 0) return fast_pow_mod (b * b % MOD, x / 2) % MOD;

		return b * fast_pow_mod (b * b % MOD, x / 2) % MOD;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String str_n = in.readLine ();

		if (str_n.length () > 6) {
			System.out.print (999999998);
			return;
		}
		else {
			int N = Integer.parseInt (str_n);
			long amt = 0;

			for (int n = 1; n <= N; ++n) {
				amt = (amt + 9 * Math.min (fast_pow_mod (10, (n + n % 2) / 2 - 1), MOD)) % MOD;
			}

			System.out.print (amt % MOD);
		}
	}
}