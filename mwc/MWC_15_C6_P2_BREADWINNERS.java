import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class MWC_15_C6_P2_BREADWINNERS {

	static boolean[] sieve;
	static int[] comp;
	static List<Integer> list;

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ()), max = -1, result;
		comp = new int[N];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			comp[n] = Integer.parseInt (t[n]);
			max = Math.max (comp[n], max);
		}

		sieve = new boolean[max + 1];
		list = new ArrayList<Integer> (max / 2);
		list.add (2);

		for (int i = 2; i <= max; i++) {
			if (!sieve[i] && i % 2 != 0) {
				for (int x = i, y = 2; x <= max; x = i * y, y++) {
					sieve[x] = true;
				}
				list.add (i);
			}
		}

		Collections.sort (list);

		for (int n = 0; n < N; n++) {
			result = Collections.binarySearch (list, comp[n]);

			if (comp[n] <= 2 || result == 0) {
				System.out.println ("no can do");
			}
			else if (result > 0) {
				System.out.println (list.get (result - 1));
			}
			else {
				System.out.println (list.get (-result - 2));
			}
		}
	}
}