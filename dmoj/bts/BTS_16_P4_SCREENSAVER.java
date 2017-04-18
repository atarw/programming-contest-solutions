import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BTS_16_P4_SCREENSAVER {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), T = Integer.parseInt (t[2]);
		char[][] maze = new char[M][N];

		int sx = -1, sy = -1;

		for (int m = 0; m < M; ++m) {
			String ln = in.readLine ();

			for (int n = 0; n < N; ++n) {
				maze[m][n] = ln.charAt (n);

				if (maze[m][n] == 'O') {
					sx = m;
					sy = n;
				}
			}
		}

		int x = 1;
		int[] dir = {0, 1};

		for (; x <= T; ++x) {
			sx += dir[0];
			sy += dir[1];

			if (!(0 <= sx && sx < M && 0 <= sy && sy < N)) {
				break;
			}

			if (maze[sx][sy] == '.') {
				continue;
			}

			if (maze[sx][sy] == '-') {
				if (dir[0] == 0) {// not going up/down
					continue;
				}
				else {
					dir = new int[] {-dir[0], 0};
					maze[sx][sy] = '|';
				}
			}
			else if (maze[sx][sy] == '|') {
				if (dir[0] != 0) {// going up
					continue;
				}
				else {
					dir = new int[] {0, -dir[1]};
					maze[sx][sy] = '-';
				}
			}
			else if (maze[sx][sy] == '/') {
				dir = new int[] {-dir[1], -dir[0]};
				maze[sx][sy] = '\\';
			}
			else if (maze[sx][sy] == '\\') {
				dir = new int[] {dir[1], dir[0]};
				maze[sx][sy] = '/';
			}
		}

		if (0 <= sx && sx < M && 0 <= sy && sy < N) {
			out.print ("The observer stays within the grid.");
		}
		else {
			out.print ("The observer leaves the grid after " + x + " tick(s).");
		}

		out.close ();
	}
}