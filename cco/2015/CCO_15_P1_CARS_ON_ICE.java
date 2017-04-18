import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class CCO_15_P1_CARS_ON_ICE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);
		char[][] maze = new char[N][M];
		int cnt = 0;

		for (int n = 0; n < N; ++n) {
			String ln = in.readLine ();

			for (int m = 0; m < M; ++m) {
				maze[n][m] = ln.charAt (m);

				if (maze[n][m] != '.')
					++cnt;
			}
		}

		Deque <Integer>[] rows = new ArrayDeque[N];
		Deque <Integer>[] cols = new ArrayDeque[M];

		for (int n = 0; n < N; ++n)
			rows[n] = new ArrayDeque <Integer> ();

		for (int m = 0; m < M; ++m)
			cols[m] = new ArrayDeque <Integer> ();

		for (int n = 0; n < N; ++n) {
			for (int m = 0; m < M; ++m) {
				if (maze[n][m] != '.') {
					rows[n].offerLast (m);
					cols[m].offerLast (n);
				}
			}
		}

		boolean[][] vis = new boolean[N][M];

		for (int c = 0; c < cnt; ) {
			out.flush ();

			for (int n = 0; n < N; ++n) {
				while (!rows[n].isEmpty ()) {
					if (vis[n][rows[n].peekFirst ()]) {
						rows[n].pollFirst ();
						continue;
					}

					if (vis[n][rows[n].peekLast ()]) {
						rows[n].pollLast ();
						continue;
					}

					if (maze[n][rows[n].peekFirst ()] == 'W') {
						vis[n][rows[n].peekFirst ()] = true;
						++c;
						out.println ("(" + n + "," + rows[n].peekFirst () + ")");
						rows[n].pollFirst ();
						continue;
					}

					if (maze[n][rows[n].peekLast ()] == 'E') {
						vis[n][rows[n].peekLast ()] = true;
						++c;
						out.println ("(" + n + "," + rows[n].peekLast () + ")");
						rows[n].pollLast ();
						continue;
					}

					break;
				}
			}

			for (int m = 0; m < M; ++m) {
				while (!cols[m].isEmpty ()) {
					if (vis[cols[m].peekFirst ()][m]) {
						cols[m].pollFirst ();
						continue;
					}

					if (vis[cols[m].peekLast ()][m]) {
						cols[m].pollLast ();
						continue;
					}

					if (maze[cols[m].peekFirst ()][m] == 'N') {
						vis[cols[m].peekFirst ()][m] = true;
						++c;
						out.println ("(" + cols[m].peekFirst () + "," + m + ")");
						cols[m].pollFirst ();
						continue;
					}

					if (maze[cols[m].peekLast ()][m] == 'S') {
						vis[cols[m].peekLast ()][m] = true;
						++c;
						out.println ("(" + cols[m].peekLast () + "," + m + ")");
						cols[m].pollLast ();
						continue;
					}

					break;
				}
			}
		}

		out.close ();
	}
}