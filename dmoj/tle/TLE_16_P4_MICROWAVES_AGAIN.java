import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class TLE_16_P4_MICROWAVES_AGAIN {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), K = Integer.parseInt (t[2]);

		int[][] matrix = new int[N][N];
		int[] mic = new int[N];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; ++n)
			mic[n] = Integer.parseInt (t[n]);

		for (int m = 0; m < M; ++m) {
			t = in.readLine ().split (" ");
			int u = Integer.parseInt (t[0]) - 1, v = Integer.parseInt (t[1]) - 1, d = Integer.parseInt (t[2]);
			matrix[u][v] = matrix[v][u] = d;
		}

		Set<Integer>[] list = new HashSet[N];
		int[][] cache = new int[N][N];

		for (int n = 0; n < N; ++n)
			Arrays.fill (cache[n], Integer.MAX_VALUE / 2);

		for (int n = 0; n < N; ++n) {
			Queue<Integer> q = new ArrayDeque<Integer> ();
			q.offer (n);
			int curr = n;
			cache[n][n] = 0;

			while (!q.isEmpty ()) {
				curr = q.poll ();

				for (int v = 0; v < N; ++v) {
					if (matrix[curr][v] != 0 && cache[n][v] >= cache[n][curr] + matrix[curr][v]) {
						q.offer (v);
						cache[n][v] = cache[n][curr] + matrix[curr][v];
					}
				}
			}

			list[n] = new HashSet<Integer> (101, 1.1f);

			for (int u = 0; u < N; ++u) {
				if (cache[n][u] <= K)
					list[n].add (u);
			}
		}

		int count = 0, max = 0;

		for (int n = 0; n < N; ++n) {
			for (int n2 = n + 1; n2 < N; ++n2) {
				for (int n3 = n2 + 1; n3 < N; ++n3) {
					for (int n4 = 0; n4 < N; ++n4) {
						if (list[n].contains (n4) || list[n2].contains (n4) || list[n3].contains (n4))
							count += mic[n4];
					}

					max = Math.max (count, max);
					count = 0;
				}
			}
		}

		out.println (max);
		out.close ();
	}
}