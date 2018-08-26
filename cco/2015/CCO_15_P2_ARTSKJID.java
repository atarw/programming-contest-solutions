import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCO_15_P2_ARTSKJID {

	static int[][] matrix;
	static int[][] cache;

	public static int toggle (int i, int vis) {
		return vis ^ 1 << i;
	}

	public static int check (int i, int vis) {
		return (vis >> i) & 1;
	}

	public static int solve (int curr, int vis) {
		if (cache[curr][vis] == 0 && curr != matrix.length - 1) {
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[curr][i] != 0 && check (i, vis) == 0) {
					cache[curr][vis] = Math.max (cache[curr][vis], matrix[curr][i] + solve (i, toggle (i, vis)));
				}
			}

			if (cache[curr][vis] == 0) {
				cache[curr][vis] = Integer.MIN_VALUE;//cannot be reached from here
			}
		}

		return cache[curr][vis];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);

		matrix = new int[N][N];
		cache = new int[N][1 << N];

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			matrix[Integer.parseInt (t[0])][Integer.parseInt (t[1])] = Integer.parseInt (t[2]);
		}

		System.out.println (solve (0, toggle (0, 0)));
	}
}