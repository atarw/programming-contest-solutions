import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class CCC_13_J4_TIME_ON_TASK {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int T = Integer.parseInt (in.readLine ());
		int C = Integer.parseInt (in.readLine ());
		int[] chores = new int[C];

		for (int c = 0; c < C; c++) {
			chores[c] = Integer.parseInt (in.readLine ());
		}

		Arrays.sort (chores);

		for (int i = 0, t = 0; i < C; t += chores[i], i++) {
			if (t + chores[i] > T) {
				System.out.println (i);
				break;
			}
		}
	}
}