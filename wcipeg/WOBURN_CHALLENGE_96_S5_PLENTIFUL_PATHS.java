import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class WOBURN_CHALLENGE_96_S5_PLENTIFUL_PATHS {

	static boolean[][] maze;
	static int[][] cache;

	public static int max (int x, int y) {
		if (cache[x][y] == 0) {
			int curr = maze[x][y] ? 1 : 0;

			if (x == maze.length - 1 && y == maze[0].length - 1) {
				return curr;
			}

			if (x + 1 < maze.length) {
				cache[x][y] = Math.max (cache[x][y], curr + max (x + 1, y));
			}

			if (y + 1 < maze[0].length) {
				cache[x][y] = Math.max (cache[x][y], curr + max (x, y + 1));
			}
		}
		return cache[x][y];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		maze = new boolean[Integer.parseInt (t[0])][Integer.parseInt (t[1])];
		cache = new int[maze.length][maze[0].length];

		while (true) {
			t = in.readLine ().split (" ");

			if (t[0].charAt (0) == '0' && t[1].charAt (0) == '0') {
				break;
			}

			maze[Integer.parseInt (t[0]) - 1][Integer.parseInt (t[1]) - 1] = true;
		}

		System.out.println (max (0, 0));
	}
}