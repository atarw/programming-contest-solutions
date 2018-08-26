import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCO_14_P1_TROYANGLES {

	static boolean[][] maze;
	static int[][] cache;

	public static int solve (int x, int y) {
		if (cache[x][y] == 0 && maze[x][y]) {
			cache[x][y] = 1;

			if (x + 1 < cache.length && y - 1 >= 0 && y + 1 < cache.length && maze[x + 1][y - 1] && maze[x + 1][y] &&
					maze[x + 1][y + 1]) {
				cache[x][y] += Math.min (Math.min (solve (x + 1, y - 1), solve (x + 1, y)), solve (x + 1, y + 1));
			}
		}

		return cache[x][y];
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		cache = new int[N][N];
		maze = new boolean[N][N];

		String ln;

		for (int n = 0; n < N; n++) {
			ln = in.readLine ();

			for (int x = 0; x < N; x++) {
				maze[n][x] = ln.charAt (x) == '#';
			}
		}

		int total = 0;

		for (int n = 0; n < N; n++) {
			for (int x = 0; x < N; x++) {
				total += solve (n, x);
			}
		}

		System.out.println (total);
	}
}