import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class VMSS_15_P2_TOMB_ROBBING {

	static boolean[][] maze;

	public static int rooms () {
		Queue<P> queue = new ArrayDeque<P> ();
		int total = 0;
		P curr;

		for (int x = 0; x < maze.length; x++) {
			for (int y = 0; y < maze[0].length; y++) {
				if (maze[x][y]) {
					queue.offer (new P (x, y));
					total++;

					while (!queue.isEmpty ()) {
						curr = queue.poll ();
						maze[curr.x][curr.y] = false;

						if (valid (curr.x + 1, curr.y)) {
							queue.offer (new P (curr.x + 1, curr.y));
						}
						if (valid (curr.x - 1, curr.y)) {
							queue.offer (new P (curr.x - 1, curr.y));
						}
						if (valid (curr.x, curr.y + 1)) {
							queue.offer (new P (curr.x, curr.y + 1));
						}
						if (valid (curr.x, curr.y - 1)) {
							queue.offer (new P (curr.x, curr.y - 1));
						}
					}
				}
			}
		}

		return total;
	}

	public static boolean valid (int r, int c) {
		return r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		String ln;

		maze = new boolean[Integer.parseInt (t[0])][Integer.parseInt (t[1])];

		for (int r = 0; r < maze.length; r++) {
			ln = in.readLine ();

			for (int c = 0; c < maze[0].length; c++) {
				maze[r][c] = ln.charAt (c) == '.';
			}
		}

		System.out.println (rooms ());
	}

	private static class P {

		int x, y;

		public P (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}