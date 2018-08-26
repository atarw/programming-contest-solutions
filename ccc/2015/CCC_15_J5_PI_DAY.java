import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_15_J5_PI_DAY {

	static int[][][] t;

	public static int pies (int N, int K, int min) {
		if (t[N - 1][K - 1][min] == 0) {
			if (K == N || K == 1) {
				t[N - 1][K - 1][min] = 1;
			}
			else {
				int total = 0;

				for (int x = min; x <= N / K; x++) {
					total += pies (N - x, K - 1, x);
				}
				t[N - 1][K - 1][min] = total;
			}
		}
		return t[N - 1][K - 1][min];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		int K = Integer.parseInt (in.readLine ());

		t = new int[N][K][N];
		System.out.println (pies (N, K, 1));
	}
}