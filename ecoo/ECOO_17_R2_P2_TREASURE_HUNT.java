import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class ECOO_17_R2_P2_TREASURE_HUNT {

	public static Pair bfs (int sx, int sy, char[][] maze, int keys) {
		boolean[][] vis = new boolean[maze.length][maze[0].length];
		int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		Queue<Pair> q = new ArrayDeque<Pair> ();
		Pair cur = new Pair (sx, sy);
		q.offer (cur);

		Pair ret = new Pair (0, 0);//key, treasure

		while (!q.isEmpty ()) {
			cur = q.poll ();
			vis[cur.x][cur.y] = true;

			for (int[] d : dir) {
				int nx = cur.x + d[0], ny = cur.y + d[1];

				if (nx < 0 || ny < 0 || nx >= maze.length || ny >= maze.length || maze[nx][ny] == '#' || vis[nx][ny])
					continue;

				boolean g = true;

				if (maze[nx][ny] == 'K')
					++ret.x;
				else if (maze[nx][ny] == 'T')
					++ret.y;
				else if (maze[nx][ny] != '.')
					if (Character.getNumericValue (maze[nx][ny]) > keys)
						g = false;

				if (g) {
					q.offer (new Pair (nx, ny));
					vis[nx][ny] = true;
				}
			}
		}

		return ret;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);
		int T = 10;

		for (int tt = 0; tt < T; ++tt) {
			int N = Integer.parseInt (in.readLine ());
			char[][] maze = new char[N][N];
			int sx = -1, sy = -1;

			for (int n = 0; n < N; ++n) {
				String ln = in.readLine ();

				for (int n2 = 0; n2 < N; ++n2) {
					maze[n][n2] = ln.charAt (n2);

					if (maze[n][n2] == 'S') {
						sx = n;
						sy = n2;
						maze[n][n2] = '.';
					}
				}
			}

			Pair ans = new Pair (0, 0);

			while (true) {
				Pair cur = bfs (sx, sy, maze, ans.x);

				if (ans.x < cur.x) {
					ans = cur;
				}
				else if (ans.x == cur.x) {
					ans = cur;
					break;
				}
			}

			out.println (ans.y);
		}

		out.close ();
	}

	private static class Pair {

		int x, y;

		public Pair (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}