import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class VMSS_15_P2_SETS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] t = new String[N];

		for (int n = 0; n < N; n++) {
			t[n] = in.readLine ();
		}

		Arrays.sort (t, new CommandOrder ());

		NavigableMap<Character, NavigableSet<Character>> map = new TreeMap<Character, NavigableSet<Character>> ();
		//stores set name to set contents
		Map<Character, Set<Character>> links = new HashMap<Character, Set<Character>> ();//stores set name to list
		// of sets which contain the key set

		for (String i : t) {
			if (!map.containsKey (i.charAt (0))) {
				map.put (i.charAt (0), new TreeSet<Character> ());
			}

			if (!Character.isUpperCase (i.charAt (i.length () - 1))) {
				map.get (i.charAt (0)).add (i.charAt (i.length () - 1));
			}
			else {
				if (!map.containsKey (i.charAt (i.length () - 1))) {
					map.put (i.charAt (i.length () - 1), new TreeSet<Character> ());
				}

				if (!links.containsKey (i.charAt (i.length () - 1))) {
					links.put (i.charAt (i.length () - 1), new HashSet<Character> ());
				}

				links.get (i.charAt (i.length () - 1)).add (i.charAt (0));
				map.get (i.charAt (0)).addAll (map.get (i.charAt (i.length () - 1)));

				if (links.containsKey (i.charAt (0))) {
					for (Character c : links.get (i.charAt (0))) {
						map.get (c).addAll (map.get (i.charAt (0)));
					}
				}
			}
		}

		for (Character i : map.keySet ()) {
			System.out.print (i + " = {");

			for (Iterator<Character> it = map.get (i).iterator (); it.hasNext (); ) {
				System.out.print (it.next ());

				if (it.hasNext ())
					System.out.print (',');
			}
			System.out.println ("}");
		}
	}

	private static class CommandOrder implements Comparator<String> {

		public int compare (String s1, String s2) {
			if (Character.isUpperCase (s1.charAt (s1.length () - 1))) {
				if (Character.isUpperCase (s2.charAt (s2.length () - 1))) {
					return s1.charAt (0) - s2.charAt (0);
				}
				return 1;
			}
			else {
				if (Character.isUpperCase (s2.charAt (s2.length () - 1))) {
					return -1;
				}
				return s1.charAt (0) - s2.charAt (0);
			}
		}

		public boolean equals (Object o) {
			String s = ((String) o);
			return this.equals (s);
		}
	}
}