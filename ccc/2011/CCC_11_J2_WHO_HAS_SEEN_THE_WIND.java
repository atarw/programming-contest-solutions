import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_11_J2_WHO_HAS_SEEN_THE_WIND {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int H = Integer.parseInt (in.readLine ());
		int M = Integer.parseInt (in.readLine ());
		boolean f = false;

		for (int i = 1; i <= M; i++) {
			if ((int) (-6 * Math.pow (i, 4) + H * Math.pow (i, 3) + 2 * Math.pow (i, 2) + i) <= 0) {
				f = true;
				System.out.println ("The balloon first touches ground at hour:\n" + i);
				break;
			}
		}

		if (!f) {
			System.out.println ("The balloon does not touch ground in the given time.");
		}
	}
}