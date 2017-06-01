import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CCC_12_S3_ABSOLUTELY_ACIDIC {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		NavigableMap<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>> ();//stores frequency to
		// readings
		Map<Integer, Integer> freq = new HashMap<Integer, Integer> ();//stores reading to frequency
		int k;

		for (int n = 0; n < N; n++) {
			k = Integer.parseInt (in.readLine ());

			if (!freq.containsKey (k)) {
				freq.put (k, 0);
			}

			freq.put (k, freq.get (k) + 1);

			if (!map.containsKey (freq.get (k))) {
				map.put (freq.get (k), new ArrayList<Integer> ());
			}

			if (freq.get (k) == 1) {//new reading
				map.get (1).add (k);
			}
			else {
				map.get (freq.get (k) - 1).remove (new Integer (k));
				map.get (freq.get (k)).add (k);

				if (map.get (freq.get (k) - 1).isEmpty ()) {
					map.remove (freq.get (k) - 1);
				}
			}
		}

		if (map.lastEntry ().getValue ().size () == 1) {//only one highest reading
			if (map.lowerEntry (map.lastKey ()).getValue ().size () == 1) {//only one second best
				System.out.println (Math.abs (map.lastEntry ().getValue ().get (0) - map.lowerEntry (map.lastKey ())
						.getValue ().get (0)));
			}
			else {
				List<Integer> list = map.lowerEntry (map.lastKey ()).getValue ();
				Collections.sort (list);
				int best = Math.max (Math.abs (map.lastEntry ().getValue ().get (0) - list.get (0)), Math.abs (map
						                                                                                               .lastEntry ().getValue ().get (0) - list.get (list.size () - 1)));

				System.out.println (best);
			}
		}
		else {//more than one highest reading
			List<Integer> list = map.lastEntry ().getValue ();
			Collections.sort (list);

			System.out.println (list.get (list.size () - 1) - list.get (0));
		}
	}
}