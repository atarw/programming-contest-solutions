import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableSet;
import java.util.TreeSet;

public class CODEJAM_ROUND_1A_16_P2_RANK_AND_FILE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		NavigableSet<Integer> set;
		int T = Integer.parseInt (in.readLine ()), N, X;

		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt (in.readLine ());
			set = new TreeSet<Integer> ();

			for (int n = 0; n < 2 * N - 1; n++) {
				t = in.readLine ().split (" ");

				for (int x = 0; x < t.length; x++) {
					X = Integer.parseInt (t[x]);

					if (!set.add (X)) {
						set.remove (X);
					}
				}
			}

			System.out.print ("Case #" + i + ":");

			for (int y : set) {
				System.out.print (" " + y);
			}
			System.out.println ();
		}
	}
}