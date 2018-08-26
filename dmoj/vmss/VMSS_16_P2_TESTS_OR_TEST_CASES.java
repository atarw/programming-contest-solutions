import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

// atharva washimkar
// August 11, 2018

public class VMSS_16_P2_TESTS_OR_TEST_CASES {

	static NavigableMap<Integer, NavigableSet<Character>> map = new TreeMap<Integer, NavigableSet<Character>> ();
	static List<String> list = new ArrayList<String> ();
	static int L;

	public static void paths (String curr, int set) {
		if (curr.length () < L) {
			for (int i = set; i <= map.lastKey (); i++) {
				if (map.containsKey (i)) {
					for (char x : map.get (i)) {
						list.add (curr + x);
						//System.out.println (curr + x);
						paths (curr + x, i + 1);
					}
				}
			}
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]);
		L = Integer.parseInt (t[1]);

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			map.put (n, new TreeSet<Character> ());

			for (int i = 1; i < t.length; i++) {
				map.get (n).add (t[i].charAt (0));
			}
		}

		for (char first : map.firstEntry ().getValue ()) {
			list.add (first + "");
			paths ("" + first, 1);
		}

		Collections.sort (list);

		for (String i : list) {
			System.out.println (i);
		}
	}
}