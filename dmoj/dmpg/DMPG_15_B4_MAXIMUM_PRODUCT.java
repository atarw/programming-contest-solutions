import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class DMPG_15_B4_MAXIMUM_PRODUCT {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		int product = 0;

		if (N != 1) {
			List<Integer> positive = new LinkedList<Integer> ();
			List<Integer> negative = new LinkedList<Integer> ();

			for (int n = 0; n < N; n++) {
				int C = Integer.parseInt (in.readLine ());

				if (C != 0) {
					if (C > 0) positive.add (C);
					else negative.add (C);
				}
			}

			for (Integer i : positive) {
				product = product == 0 ? i : product * i;
			}

			Collections.sort (negative, Collections.reverseOrder ());

			if (negative.size () % 2 == 1) {
				negative.remove (0);
			}

			for (Integer i : negative) {
				product = product == 0 ? i : product * i;
			}
		}
		else {
			product = Integer.parseInt (in.readLine ());
		}
		System.out.println (product);
	}
}