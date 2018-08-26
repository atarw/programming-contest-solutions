import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CCC_00_S4_GOLF {

	static int[] cache;
	static int[] clubs;

	public static int min (int D) {
		if (cache[D] == 0 && D != 0) {
			cache[D] = Integer.MAX_VALUE - 1;

			for (int i : clubs) {
				if (D >= i) {
					cache[D] = Math.min (cache[D], 1 + min (D - i));
				}
				else {
					break;
				}
			}
		}
		return cache[D];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int D = Integer.parseInt (in.readLine ()), N = Integer.parseInt (in.readLine ());

		cache = new int[D + 1];
		clubs = new int[N];

		for (int n = 0; n < N; n++) {
			clubs[n] = Integer.parseInt (in.readLine ());
		}

		Arrays.sort (clubs);
		int strokes = min (D);

		System.out.print ("Roberta " + (strokes == Integer.MAX_VALUE - 1 ? "acknowledges defeat." : "wins in " +
				strokes + " strokes."));
	}
}