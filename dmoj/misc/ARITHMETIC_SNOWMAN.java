import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// atharva washimkar
// August 11, 2018

public class ARITHMETIC_SNOWMAN {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] t = in.readLine ().split (" ");

		List<Integer> list = new ArrayList<Integer> (N);
		Set<Integer> set = new HashSet<Integer> (N);

		for (String i : t) {
			list.add (Integer.parseInt (i));
			set.add (list.get (list.size () - 1));
		}

		Collections.sort (list, Collections.reverseOrder ());

		int ball1 = 0, ball2 = 0, ball3 = 0;
		int diff = 1;

		int max = 0;

		out:
		for (int i = 0; i < list.size (); i++) {
			ball1 = list.get (i);

			for (int x = i + 1; x < list.size (); x++) {

				if (list.get (x) == ball1) {
					continue;
				}
				else {
					ball2 = list.get (x);
					diff = ball1 - list.get (x);

					if (set.contains (ball2 - diff)) {
						ball3 = ball2 - diff;
						max = Math.max (max, ball1 + ball2 + ball3);
						continue out;
					}
					else {
						continue;
					}
				}
			}
		}

		//System.out.println (ball1 + " " + ball2 + " " + ball3);
		System.out.println (ball3 != 0 ? max : 0);
	}
}