import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FLOYD_WARSHALL {

	static int[][] matrix;

	public static void fw () {//matrix [i][i] < 0 = negative cycle
		for (int k = 0; k < matrix.length; k++) {
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);

		matrix = new int[N][N];

		for (int i = 0; i < matrix.length; i++) {
			Arrays.fill (matrix[i], 10001);
			matrix[i][i] = 0;
		}

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			matrix[Integer.parseInt (t[0]) - 1][Integer.parseInt (t[1]) - 1] = Integer.parseInt (t[2]);
		}

		fw ();

		for (int[] arr : matrix) {
			for (int i : arr) {
				System.out.print (i + " ");
			}
			System.out.println ();
		}
	}
}