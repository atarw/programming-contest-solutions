import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DMOPC_13_P3_CROSSING_FIELD {

	static P[][] cache;
	static int[][] maze;
	static Set<P> set;
	static int H, N;

	public static boolean path (int r, int c, int xd, int yd) {
		if (cache[r][c].equals (cache[xd][yd])) {
			return true;
		}
		else if (set.contains (cache[r][c])) {
			return false;
		}
		else {
			set.add (cache[r][c]);

			if (valid (r - 1, c, r, c) && path (r - 1, c, xd, yd)) {
				return true;
			}
			if (valid (r + 1, c, r, c) && path (r + 1, c, xd, yd)) {
				return true;
			}
			if (valid (r, c + 1, r, c) && path (r, c + 1, xd, yd)) {
				return true;
			}
			if (valid (r, c - 1, r, c) && path (r, c - 1, xd, yd)) {
				return true;
			}
		}
		return false;
	}

	public static boolean valid (int r, int c, int r1, int c1) {
		return 0 <= r && r < maze.length && 0 <= c && c < maze[0].length && Math.abs (maze[r1][c1] - maze[r][c]) <= H
				&& !set.contains (cache[r][c]);
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		N = Integer.parseInt (t[0]);
		H = Integer.parseInt (t[1]);

		maze = new int[N][N];
		cache = new P[N][N];
		set = new HashSet<P> (N);

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			for (int i = 0; i < N; i++) {
				maze[n][i] = Integer.parseInt (t[i]);
				cache[n][i] = new P (n, i);
			}
		}

		System.out.println (path (0, 0, N - 1, N - 1) ? "yes" : "no");
	}

	private static class P {

		int X, Y;

		public P (int X, int Y) {
			this.X = X;
			this.Y = Y;
		}

		public int hashCode () {
			return X * 17 + Y * 37;
		}

		public boolean equals (Object o) {
			P a = (P) (o);

			return X == a.X && Y == a.Y;
		}

		public String toString () {
			return X + " " + Y;
		}
	}
}