import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class SOKO_BOOP {

	static int[][][][] cache;
	static char[][] grid;

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int R = Integer.parseInt (t[0]), C = Integer.parseInt (t[1]);
		cache = new int[R][C][R][C];
		grid = new char[R][C];

		String ln;

		int ex = -1, ey = -1;
		State curr = new State ();

		for (int r = 0; r < R; ++r) {
			ln = in.readLine ();

			for (int c = 0; c < C; ++c) {
				grid[r][c] = ln.charAt (c);

				if (ln.charAt (c) == 'B') {
					curr.bx = r;
					curr.by = c;
				}
				else if (ln.charAt (c) == 'X') {
					ex = r;
					ey = c;
				}
				else if (ln.charAt (c) == 'P') {
					curr.px = r;
					curr.py = c;
				}
			}
		}

		Queue<State> q = new ArrayDeque<State> ();

		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c) {
				for (int r2 = 0; r2 < R; ++r2) {
					for (int c2 = 0; c2 < C; ++c2) {
						cache[r][c][r2][c2] = Integer.MAX_VALUE - 10000;
					}
				}
			}
		}

		cache[curr.px][curr.py][curr.bx][curr.by] = 0;
		q.offer (curr);
		int min = Integer.MAX_VALUE;

		while (!q.isEmpty ()) {
			curr = q.poll ();

			if (curr.bx == ex && curr.by == ey) {
				min = Math.min (min, cache[curr.px][curr.py][curr.bx][curr.by]);
			}
			else {
				if (curr.px + 1 < R && grid[curr.px + 1][curr.py] != '#') {
					if (curr.px + 1 == curr.bx && curr.py == curr.by) {
						if (curr.bx + 1 < R && grid[curr.bx + 1][curr.by] != '#' && cache[curr.px + 1][curr.py][curr
								.bx + 1][curr.by] > cache[curr.px][curr.py][curr.bx][curr.by] + 1) {
							cache[curr.px + 1][curr.py][curr.bx + 1][curr.by] = cache[curr.px][curr.py][curr.bx][curr
									.by] + 1;
							q.offer (new State (curr.px + 1, curr.py, curr.bx + 1, curr.by));
						}
					}
					else if (cache[curr.px + 1][curr.py][curr.bx][curr.by] > cache[curr.px][curr.py][curr.bx][curr.by]
							+ 1) {
						cache[curr.px + 1][curr.py][curr.bx][curr.by] = cache[curr.px][curr.py][curr.bx][curr.by] + 1;
						q.offer (new State (curr.px + 1, curr.py, curr.bx, curr.by));
					}
				}

				if (curr.px - 1 >= 0 && grid[curr.px - 1][curr.py] != '#') {
					if (curr.bx == curr.px - 1 && curr.by == curr.py) {
						if (curr.bx - 1 >= 0 && grid[curr.bx - 1][curr.by] != '#' && cache[curr.px - 1][curr.py][curr
								.bx - 1][curr.by] > cache[curr.px][curr.py][curr.bx][curr.by] + 1) {
							cache[curr.px - 1][curr.py][curr.bx - 1][curr.by] = cache[curr.px][curr.py][curr.bx][curr
									.by] + 1;
							q.offer (new State (curr.px - 1, curr.py, curr.bx - 1, curr.by));
						}
					}
					else if (cache[curr.px - 1][curr.py][curr.bx][curr.by] > cache[curr.px][curr.py][curr.bx][curr.by]
							+ 1) {
						cache[curr.px - 1][curr.py][curr.bx][curr.by] = cache[curr.px][curr.py][curr.bx][curr.by] + 1;
						q.offer (new State (curr.px - 1, curr.py, curr.bx, curr.by));
					}
				}

				if (curr.py + 1 < C && grid[curr.px][curr.py + 1] != '#') {
					if (curr.bx == curr.px && curr.by == curr.py + 1) {
						if (curr.by + 1 < C && grid[curr.bx][curr.by + 1] != '#' && cache[curr.px][curr.py + 1][curr
								.bx][curr.by + 1] > cache[curr.px][curr.py][curr.bx][curr.by] + 1) {
							cache[curr.px][curr.py + 1][curr.bx][curr.by + 1] = cache[curr.px][curr.py][curr.bx][curr
									.by] + 1;
							q.offer (new State (curr.px, curr.py + 1, curr.bx, curr.by + 1));
						}
					}
					else if (cache[curr.px][curr.py + 1][curr.bx][curr.by] > cache[curr.px][curr.py][curr.bx][curr.by]
							+ 1) {
						cache[curr.px][curr.py + 1][curr.bx][curr.by] = cache[curr.px][curr.py][curr.bx][curr.by] + 1;
						q.offer (new State (curr.px, curr.py + 1, curr.bx, curr.by));
					}
				}

				if (curr.py - 1 >= 0 && grid[curr.px][curr.py - 1] != '#') {
					if (curr.bx == curr.px && curr.by == curr.py - 1) {
						if (curr.by - 1 >= 0 && grid[curr.bx][curr.by - 1] != '#' && cache[curr.px][curr.py - 1][curr
								.bx][curr.by - 1] > cache[curr.px][curr.py][curr.bx][curr.by] + 1) {
							cache[curr.px][curr.py - 1][curr.bx][curr.by - 1] = cache[curr.px][curr.py][curr.bx][curr
									.by] + 1;
							q.offer (new State (curr.px, curr.py - 1, curr.bx, curr.by - 1));
						}
					}
					else if (cache[curr.px][curr.py - 1][curr.bx][curr.by] > cache[curr.px][curr.py][curr.bx][curr.by]
							+ 1) {
						cache[curr.px][curr.py - 1][curr.bx][curr.by] = cache[curr.px][curr.py][curr.bx][curr.by] + 1;
						q.offer (new State (curr.px, curr.py - 1, curr.bx, curr.by));
					}
				}
			}
		}

		out.print (min == Integer.MAX_VALUE ? -1 : min);
		out.close ();
	}

	private static class State {

		int px, py, bx, by;

		public State () {}

		public State (int px, int py, int bx, int by) {
			this.px = px;
			this.py = py;
			this.bx = bx;
			this.by = by;
		}
	}
}