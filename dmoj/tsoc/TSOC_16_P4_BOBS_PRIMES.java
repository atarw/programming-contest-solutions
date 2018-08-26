import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class TSOC_16_P4_BOBS_PRIMES {

	static int N, M;
	static int[] arr;
	static boolean[] sieve;
	static int[][] cache;

	public static int way (int m, int spent) {
		if (cache[m][spent] != -1)
			return cache[m][spent];

		if (m == M)
			return cache[m][spent] = sieve[spent] ? 1 : 0;

		cache[m][spent] = way (m + 1, spent);

		for (int q = 2; spent + arr[m] * q <= N; ++q) {
			if (sieve[q]) {
				cache[m][spent] = Math.max (cache[m][spent], way (m + 1, spent + arr[m] * q));
			}
		}

		return cache[m][spent];
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		N = Integer.parseInt (in.readLine ().trim ());
		M = Integer.parseInt (in.readLine ().trim ());

		arr = new int[M];
		sieve = new boolean[N + 1];

		for (int m = 0; m < M; ++m)
			arr[m] = Integer.parseInt (in.readLine ().trim ());

		Arrays.fill (sieve, true);
		sieve[0] = sieve[1] = false;

		for (int n = 2; n <= N; ++n) {
			if (sieve[n]) {
				for (int i = 2; i * n <= N; ++i) {
					sieve[i * n] = false;
				}
			}
		}

		cache = new int[M + 1][N + 1];

		for (int m = 0; m < cache.length; ++m)
			Arrays.fill (cache[m], -1);

		int sum = 0;

		for (int m = 0; m < M; ++m)
			sum += arr[m];

		if (sum * 2 > N || way (0, sum * 2) == 0) {
			out.print ("not primetime");
		}
		else {
			out.print ("its primetime");
		}

		out.close ();
	}
}