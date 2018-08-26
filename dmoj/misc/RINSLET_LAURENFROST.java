import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RINSLET_LAURENFROST {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String ln1 = in.readLine (), ln2 = in.readLine ();

		Map<Character, Integer> map = new HashMap<Character, Integer> ();
		Map<Character, Integer> map2 = new HashMap<Character, Integer> ();
		int S = 0;

		for (int i = 0; i < ln1.length (); i++) {
			if (!map.containsKey (ln1.charAt (i))) {
				map.put (ln1.charAt (i), 1);
			}
			else {
				map.put (ln1.charAt (i), map.get (ln1.charAt (i)) + 1);
			}
		}

		for (int i = 0; i < ln2.length (); i++) {
			if (!map2.containsKey (ln2.charAt (i))) {
				map2.put (ln2.charAt (i), 1);
			}
			else {
				map2.put (ln2.charAt (i), map2.get (ln2.charAt (i)) + 1);
			}
		}

		for (Character i : map.keySet ()) {
			if (map2.containsKey (i)) {
				S += Math.abs (map.get (i) - map2.get (i));
			}
			else {
				S += map.get (i);
			}
			map2.remove (i);
		}

		for (Character i : map2.keySet ()) {
			S += map2.get (i);
		}

		// System.out.println (map);
		//System.out.println (map2);
		System.out.println (S);
	}
}