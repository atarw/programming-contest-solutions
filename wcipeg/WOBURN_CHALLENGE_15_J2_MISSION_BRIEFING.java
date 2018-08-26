import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// atharva washimkar
// August 11, 2018

public class WOBURN_CHALLENGE_15_J2_MISSION_BRIEFING {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		HashSet<Integer> set = new HashSet<Integer> ();
		String ln = in.readLine ();

		int index = 0;

		for (int i = 1; i <= 9; i++) {
			index = ln.indexOf ("00" + i);

			if (index != -1) {
				set.add (i);
			}
		}

		System.out.println (set.size ());
	}
}