import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class DMOPC_14_P5_SURPRISE_TELEPORT {

	static boolean[][] maze;
	static int[][] cache;

	public static void traverse (int sX, int sY) {
		Queue<P> queue = new ArrayDeque<P> ();
		List<P> list;
		P curr;

		queue.offer (new P (sX, sY));

		for (int m = 0; m < cache.length; m++) {
			Arrays.fill (cache[m], Integer.MAX_VALUE);
		}

		cache[sX][sY] = 0;

		while (!queue.isEmpty ()) {
			curr = queue.poll ();
			list = neighbours (curr.X, curr.Y);

			for (P p : list) {
				if (cache[p.X][p.Y] > cache[curr.X][curr.Y] + 1) {
					cache[p.X][p.Y] = cache[curr.X][curr.Y] + 1;
					queue.offer (p);
				}
			}
		}
	}

	public static List<P> neighbours (int sX, int sY) {
		List<P> list = new ArrayList<P> ();

		if (sX + 1 < maze.length && maze[sX + 1][sY]) {
			list.add (new P (sX + 1, sY));
		}
		if (sX - 1 >= 0 && maze[sX - 1][sY]) {
			list.add (new P (sX - 1, sY));
		}
		if (sY + 1 < maze[0].length && maze[sX][sY + 1]) {
			list.add (new P (sX, sY + 1));
		}
		if (sY - 1 >= 0 && maze[sX][sY - 1]) {
			list.add (new P (sX, sY - 1));
		}

		return list;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		String ln;

		maze = new boolean[Integer.parseInt (t[0])][Integer.parseInt (t[1])];
		cache = new int[maze.length][maze[0].length];

		t = in.readLine ().split (" ");
		int sX = Integer.parseInt (t[0]), sY = Integer.parseInt (t[1]);

		t = in.readLine ().split (" ");
		int dX = Integer.parseInt (t[0]), dY = Integer.parseInt (t[1]);

		for (int r = 0; r < maze.length; r++) {
			ln = in.readLine ();

			for (int c = 0; c < maze[0].length; c++) {
				maze[r][c] = ln.charAt (c) == 'O';
			}
		}

		traverse (sX, sY);

		int T = Integer.parseInt (in.readLine ());
		int norm = cache[dX][dY], min = norm;

		for (int i = 0; i < T; i++) {
			t = in.readLine ().split (" ");
			min = Math.min (min, cache[Integer.parseInt (t[0])][Integer.parseInt (t[1])]);
		}

		System.out.print (norm - min);
	}

	private static class P {

		int X, Y;

		public P (int X, int Y) {
			this.X = X;
			this.Y = Y;
		}
	}
}