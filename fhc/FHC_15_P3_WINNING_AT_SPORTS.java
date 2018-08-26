import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class FHC_15_P3_WINNING_AT_SPORTS {

	static int[][] cache;

	public static int stress (int M, int O) {
		if (cache[M][O] == 0 && !(M == cache.length - 1 && O == cache[0].length - 1)) {
			if (O == cache[0].length - 1) {
				cache[M][O] = 1;
			}
			else if (M < O) {
				cache[M][O] = stress (M + 1, O) + stress (M, O + 1);
			}
			else if (M == O) {
				cache[M][O] = stress (M, O + 1);
			}
		}

		return cache[M][O] % 1000000007;
	}

	public static int free (int M, int O) {
		if (cache[M][O] == 0 && !(M == cache.length - 1 && O == cache[0].length - 1)) {
			if (M == cache.length - 1 || O == cache[0].length - 1) {
				cache[M][O] = 1;
			}
			else if (M == 0 || O + 1 == M) {
				cache[M][O] = free (M + 1, O);
			}
			else {
				cache[M][O] = free (M + 1, O) + free (M, O + 1);
			}
		}

		return cache[M][O] % 1000000007;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int T = Integer.parseInt (in.readLine ()), stress, free;

		for (int i = 1; i <= T; i++) {
			t = in.readLine ().split ("-");
			cache = new int[Integer.parseInt (t[0]) + 1][Integer.parseInt (t[1]) + 1];
			free = free (0, 0);

			cache = new int[cache.length][cache[0].length];
			stress = stress (0, 0);

			System.out.println ("Case #" + i + ": " + free + " " + stress);
		}
	}
}