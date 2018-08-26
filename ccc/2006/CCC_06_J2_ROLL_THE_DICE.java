import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_06_J2_ROLL_THE_DICE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int M = Integer.parseInt (in.readLine ());
		int N = Integer.parseInt (in.readLine ());
		int W = 0;


		for (int i = 1; i <= Math.min (M, N); i++) {
			if (10 - i <= Math.max (M, N) && 10 - i > 0) {
				W++;
			}
		}

		System.out.println ("There " + (W == 1 ? "is" : "are") + " " + W + " way" + (W == 1 ? "" : "s") + " to get " +
				                    "the" +
				                    " sum 10.");
	}
}