import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FAST_FACTORIAL_CALCULATOR {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		long[] cache = new long[34];
		cache[0] = 1;
		long mod = 1;
		int N = Integer.parseInt (in.readLine ()), v;
		String ln;

		for (int i = 0; i < 32; i++) {
			mod *= 2;
		}

		for (int i = 1; i < cache.length; i++) {
			cache[i] = i * cache[i - 1] % mod;
		}

		for (int n = 0; n < N; n++) {
			ln = in.readLine ();

			if (ln.length () < 3 && (v = Integer.parseInt (ln)) < cache.length) {
				System.out.println (cache[v]);
			}
			else {
				System.out.println (0);
			}
		}
	}
}