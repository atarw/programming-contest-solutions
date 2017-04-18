import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DMOPC_15_P4_CONTAGION {

	static long[] cache;
	static int[] x, y;
	static boolean[] visited;

	public static void traverse (int S) {
		cache[S] = 0;

		int min = -1;

		for (int i = 0; i < cache.length - 1; i++) {
			min = -1;

			for (int x = 0; x < cache.length; x++) {
				if (!visited[x] && (min == -1 || cache[x] < cache[min])) {
					min = x;
				}
			}

			visited[min] = true;

			for (int x = 0; x < cache.length; x++) {
				cache[x] = Math.min (cache[x], cache[min] + dist (min, x));
			}
		}

		Arrays.sort (cache);
	}

	public static int binary (long H) {
		int low = 0, mid = cache.length / 2, high = cache.length - 1;

		while (low <= high) {
			if (cache[mid] <= H) {
				low = mid + 1;
			}
			else if (cache[mid] > H) {
				high = mid - 1;
			}

			mid = (low + high) / 2;
		}

		return mid + 1;
	}

	public static long dist (int a, int b) {
		long diff1 = x[a] - x[b], diff2 = y[a] - y[b];
		return diff1 * diff1 + diff2 * diff2;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		x = new int[N];
		y = new int[N];
		cache = new long[N];
		visited = new boolean[N];

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			x[n] = Integer.parseInt (t[0]);
			y[n] = Integer.parseInt (t[1]);
			cache[n] = Long.MAX_VALUE;
		}

		traverse (Integer.parseInt (in.readLine ()) - 1);
		int Q = Integer.parseInt (in.readLine ());

		for (int q = 0; q < Q; q++) {
			System.out.println (binary (Long.parseLong (in.readLine ())));
		}
	}
}