import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class TSOC_16_P4_ALEX_AND_ANIMAL_RIGHTS {

	static char[][] maze;
	static boolean[][] visited;

	public static boolean valid (int r, int c) {
		return r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] != '#' && !visited[r][c];
	}

	public static List<P> next (int r, int c) {
		List<P> n = new ArrayList<P> ();

		if (valid (r + 1, c)) {
			n.add (new P (r + 1, c));
		}
		if (valid (r - 1, c)) {
			n.add (new P (r - 1, c));
		}
		if (valid (r, c + 1)) {
			n.add (new P (r, c + 1));
		}
		if (valid (r, c - 1)) {
			n.add (new P (r, c - 1));
		}

		return n;
	}

	public static boolean monkeys (int r, int c) {
		Queue<P> queue = new ArrayDeque<P> ();
		List<P> list;
		boolean m = false;
		P curr = new P (r, c);

		queue.offer (curr);
		visited[curr.X][curr.Y] = true;

		while (!queue.isEmpty ()) {
			curr = queue.poll ();

			if (maze[curr.X][curr.Y] == 'M') {
				m = true;
			}

			list = next (curr.X, curr.Y);

			for (P p : list) {
				queue.offer (p);
				visited[p.X][p.Y] = true;
			}
		}

		return m;
	}

	public static int cages () {
		int cages = 0;

		for (int r = 0; r < maze.length; r++) {
			for (int c = 0; c < maze[0].length; c++) {
				if (maze[r][c] != '#' && !visited[r][c] && monkeys (r, c)) {
					cages++;
				}
			}
		}

		return cages;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");

		int R = Integer.parseInt (t[0]);
		int C = Integer.parseInt (t[1]);

		maze = new char[R][C];
		visited = new boolean[R][C];

		String ln;

		for (int r = 0; r < R; r++) {
			ln = in.readLine ();

			for (int c = 0; c < C; c++) {
				maze[r][c] = ln.charAt (c);
			}
		}

		System.out.println (cages ());
	}

	private static class P {

		int X, Y;

		public P (int X, int Y) {
			this.X = X;
			this.Y = Y;
		}
	}
}