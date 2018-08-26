import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CCC_16_S4_COMBINING_RICEBALLS {

	static int[][] cache;
	static int[] arr;//prefix sum

	public static int f (int x, int y) {
		if (cache[x][y] == -1) {
			if (x >= y) {
				cache[x][y] = 1;
			}
			else if (y - x == 1) {
				cache[x][y] = arr[y] - arr[x] == arr[x] - arr[x - 1] ? 1 : 0;
			}
			else {
				int b = y;
				cache[x][y] = 0;

				out:
				for (int a = x; a < y; ++a) {
					for (; b > x && arr[a] - arr[x - 1] > arr[y] - arr[b - 1]; --b) {}
					if (arr[a] - arr[x - 1] == arr[y] - arr[b - 1]) {
						cache[x][y] = Math.max (cache[x][y], f (x, a) * f (a + 1, b - 1) * f (b, y));

						if (cache[x][y] == 1) {
							break out;
						}
					}
				}
			}
		}

		return cache[x][y];
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ()), best = 0;
		cache = new int[N + 1][N + 1];
		arr = new int[N + 1];

		t = in.readLine ().split (" ");

		for (int n = 1; n <= N; n++) {
			arr[n] = arr[n - 1] + Integer.parseInt (t[n - 1]);
			best = Math.max (best, arr[n] - arr[n - 1]);
		}

		for (int n = 0; n <= N; ++n) {
			Arrays.fill (cache[n], -1);
		}

		for (int x = 1; x <= N; x++) {
			for (int y = x + 1; y <= N; y++) {
				best = Math.max (best, f (x, y) * (arr[y] - arr[x - 1]));
			}
		}

		System.out.print (best);
	}
}