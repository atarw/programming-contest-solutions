import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P3_GOLDEN_LILY {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int L = Integer.parseInt (t[0]), D = Integer.parseInt (t[1]);

		int[][] maze = new int[D][L];
		int[][] cache = new int[D][L];

		for (int d = 0; d < D; ++d) {
			t = in.readLine ().split (" ");
			Arrays.fill (cache[d], Short.MAX_VALUE);

			for (int l = 0; l < L; ++l) {
				maze[d][l] = Integer.parseInt (t[l]);
			}
		}

		cache[0][0] = maze[0][0];

		for (int d = 0; d < D; ++d) {
			for (int l = 0; l < L; ++l) {
				if (d != 0) {
					cache[d][l] = Math.min (cache[d][l], cache[d - 1][l] + maze[d][l]); // down
				}

				if (l != 0) {
					cache[d][l] = Math.min (cache[d][l], cache[d][l - 1] + maze[d][l]); // left
				}
			}

			for (int l = L - 2; l >= 0; --l) {
				cache[d][l] = Math.min (cache[d][l], cache[d][l + 1] + maze[d][l]); // right
			}
		}

		t = in.readLine ().split (" ");
		out.print (cache[Integer.parseInt (t[1])][Integer.parseInt (t[0])]);
		out.close ();
	}
}