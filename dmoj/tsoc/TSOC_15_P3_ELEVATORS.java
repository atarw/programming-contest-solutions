import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TSOC_15_P3_ELEVATORS {

	public static final NavigableMap<Integer, NavigableSet<Integer>> map = new TreeMap<Integer, NavigableSet
			<Integer>> ();

	public static int g (int S, int E) {
		Integer low, high;
		int returned = 0;

		low = map.floorKey (S);
		high = map.ceilingKey (S);

		if (high != null && /*!map.get (high).isEmpty () &&*/ (low == null || high - S < S - low)) {
			returned = map.get (high).last ();

			if (map.get (high).size () == 1) {
				map.remove (high);
			}
			else {
				map.get (high).remove (map.get (high).last ());
			}
		}
		else if (low != null && /*!map.get (low).isEmpty () &&*/ (high == null || high - S > S - low)) {
			returned = map.get (low).last ();

			if (map.get (low).size () == 1) {
				map.remove (low);
			}
			else {
				map.get (low).remove (map.get (low).last ());
			}
		}
		else if (low != null && high != null /*&& !map.get (high).isEmpty () && !map.get (low).isEmpty ()*/ && high -
				S == S - low) {
			returned = Math.max (map.get (low).last (), map.get (high).last ());
			Integer toRemove = (returned == map.get (low).last () ? low : high);

			if (map.get (toRemove).size () == 1) {
				map.remove (toRemove);
			}
			else {
				map.get (toRemove).remove (map.get (toRemove).last ());
			}
		}


		//map.put (E, returned);

		if (!map.containsKey (E)) {
			NavigableSet<Integer> set = new TreeSet<Integer> ();
			set.add (returned);
			map.put (E, set);
		}
		else {
			map.get (E).add (returned);
		}


		return returned;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		int S, E;
		String[] t;

		for (int i = 1; i <= N; i++) {
			int floor = Integer.parseInt (in.readLine ());

			if (!map.containsKey (floor)) {
				NavigableSet<Integer> set = new TreeSet<Integer> ();
				set.add (i);
				map.put (floor, set);
			}
			else {
				map.get (floor).add (i);
			}
		}

		int R = Integer.parseInt (in.readLine ());

		for (int i = 0; i < R; i++) {
			t = in.readLine ().split (" ");
			S = Integer.parseInt (t[0]);
			E = Integer.parseInt (t[1]);

			System.out.println (g (S, E));
		}
	}
}