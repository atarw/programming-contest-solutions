import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P1_LOGGING_LOG {

	public static int sum (int[] M) {
		int sum = 0;

		for (int i : M) {
			sum += i;
		}

		return sum;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int D = Integer.parseInt (in.readLine ());
		int T;
		int[] M;

		for (int x = 0; x < D; x++) {
			T = Integer.parseInt (in.readLine ());
			M = new int[T];

			for (int y = 0; y < T; y++) {
				M[y] = Integer.parseInt (in.readLine ());
			}

			int sum = sum (M);

			System.out.println (sum == 0 ? "Weekend" : "Day " + (x + 1) + ": " + sum);
		}
	}
}