import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class MIA {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		while (true) {
			t = in.readLine ().split (" ");

			if (t[0].equals (t[1]) && t[1].equals (t[2]) && t[2].equals (t[3]) && t[3].equals ("0")) break;

			String[] tt = {t[0], t[1]};
			Arrays.sort (tt);

			String[] ttt = {t[2], t[3]};
			Arrays.sort (ttt);

			String p1 = tt[1] + tt[0];
			String p2 = ttt[1] + ttt[0];

			int win = -1;

			if (p1.equals ("21") || p2.equals ("21")) {
				if (!p1.equals ("21"))
					win = 2;
				else if (!p2.equals ("21"))
					win = 1;
				else
					win = 0;
			}

			if (win == -1) {
				String[] doubles = {"11", "22", "33", "44", "55", "66"};

				if (Arrays.binarySearch (doubles, p1) >= 0 || Arrays.binarySearch (doubles, p2) >= 0) {
					if (Arrays.binarySearch (doubles, p1) < 0)
						win = 2;
					else if (Arrays.binarySearch (doubles, p2) < 0)
						win = 1;
					else
						win = p1.compareTo (p2) > 0 ? 1 : p1.compareTo (p2) < 0 ? 2 : 0;
				}
			}

			if (win == -1) {
				if (p1.compareTo (p2) > 0)
					win = 1;
				else if (p1.compareTo (p2) < 0)
					win = 2;
				else
					win = 0;
			}

			if (win == 0)
				System.out.println ("Tie.");
			else
				System.out.println ("Player " + win + " wins.");
		}
	}
}
