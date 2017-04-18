import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CODEJAM_QR_16_P1_COUNTING_SHEEP {

	public static long result (int N) {
		if (N == 0) {
			return -1L;
		}

		boolean[] cache = new boolean[10];
		int seen = 0;
		long num = 0L;
		String numstr;

		for (int n = 1; seen < 10; n++) {
			num = (long) (N) * n;
			numstr = Long.toString (num);

			for (int i = 0; i < numstr.length (); i++) {
				if (!cache[Character.getNumericValue (numstr.charAt (i))]) {
					seen++;
				}
				cache[Character.getNumericValue (numstr.charAt (i))] = true;
			}
		}

		return num;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int T = Integer.parseInt (in.readLine ());
		long r = 0;

		for (int t = 1; t <= T; t++) {
			r = result (Integer.parseInt (in.readLine ()));
			System.out.println ("Case #" + t + ": " + (r == -1 ? "INSOMNIA" : r));
		}
	}
}