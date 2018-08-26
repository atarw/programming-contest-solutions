//quite possibly some of the worst code i have ever written in my life

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class HIDE_N_SEEK {

	static char[][] maze;
	static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int[][][] cache;
	static boolean[][] vis;
	static int[][] id;

	public static boolean valid (int x, int y) {
		return 0 <= x && x < maze.length && 0 <= y && y < maze[0].length && maze[x][y] != 'X';
	}

	public static List<State> next (int x, int y) {
		List<State> next = new ArrayList<State> ();

		for (int d = 0; d < dir.length; ++d) {
			if (valid (x + dir[d][0], y + dir[d][1])) {
				next.add (new State (x + dir[d][0], y + dir[d][1]));
			}
		}

		return next;
	}

	public static void bfs (int j, int x, int y) {
		Queue<State> q = new ArrayDeque<State> ();
		State curr = new State (x, y);
		cache[j][x][y] = 0;
		q.offer (curr);

		List<State> next;

		while (!q.isEmpty ()) {
			curr = q.poll ();
			next = next (curr.x, curr.y);

			for (State s : next) {
				if (cache[j][s.x][s.y] > cache[j][curr.x][curr.y] + 1) {
					cache[j][s.x][s.y] = cache[j][curr.x][curr.y] + 1;
					q.offer (s);
				}
			}
		}
	}

	public static int dfs (int t, int x, int y) {
		if (t == 0) {
			return 0;
		}

		vis[x][y] = true;
		int min = Integer.MAX_VALUE;

		for (int n = 0; n < maze.length; ++n) {
			for (int m = 0; m < maze[0].length; ++m) {
				if (maze[n][m] == 'H' && !vis[n][m]) {// if found a hider not found yet
					min = Math.min (min, cache[id[x][y]][n][m] + dfs (t - 1, n, m));
				}
			}
		}

		vis[x][y] = false;
		return min;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), T = Integer.parseInt (t[2]);
		maze = new char[N][M];
		vis = new boolean[N][M];
		id = new int[N][M];

		int[][] pos = new int[T][2];//[0] = x, [1] = y -> hiders
		int gx = 0, gy = 0;
		int i = 0;

		String ln;

		//input
		for (int n = 0; n < N; ++n) {
			ln = in.readLine ();

			for (int m = 0; m < M; ++m) {
				maze[n][m] = ln.charAt (m);

				if (maze[n][m] == 'H') {
					pos[i][0] = n;
					pos[i][1] = m;
					id[n][m] = i;
					++i;
				}
				else if (maze[n][m] == 'G') {
					gx = n;
					gy = m;
					id[n][m] = T;
				}
			}
		}

		//init cache
		cache = new int[T + 1][N][M];//distance from pos [t][0], pos [t][1] to [n][m] (t = T -> griffy)

		for (int x = 0; x <= T; ++x)
			for (int y = 0; y < N; ++y)
				for (int z = 0; z < M; ++z)
					cache[x][y][z] = Integer.MAX_VALUE - 10000;

		//find shortest paths from each hider to everything else
		for (int j = 0; j < T; ++j) {
			bfs (j, pos[j][0], pos[j][1]);
		}

		//find shortest paths from griffy to everything else
		bfs (T, gx, gy);

		/*for (int x = 0; x <= T; ++x) {

			if (x != T)
				out.println (pos [x][0] + " " + pos [x][1]);
			else
				out.println (gx + " " + gy);

			for (int n = 0; n < N; ++n) {
				for (int m = 0; m < M; ++m) {
					out.print ((cache [x][n][m] == Integer.MAX_VALUE - 10000 ? "X" : cache [x][n][m]) + " ");
				}
				out.println ();
			}
			out.println ();
		}*/

		//'compress' graph so that griffy and hiders are the only vertices, find min time to traverse the entire graph
		out.print (dfs (T, gx, gy));
		out.close ();
	}

	private static class State {

		int x, y;

		public State (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}