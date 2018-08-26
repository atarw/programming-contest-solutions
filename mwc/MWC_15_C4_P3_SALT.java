import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MWC_15_C4_P3_SALT {

	static Map<Integer, Set<Integer>> x = new HashMap<Integer, Set<Integer>> ();
	static Map<Integer, Set<Integer>> y = new HashMap<Integer, Set<Integer>> ();

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), Q = Integer.parseInt (t[1]);
		int X, Y;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			X = Integer.parseInt (t[0]);
			Y = Integer.parseInt (t[1]);

			if (!x.containsKey (X)) {
				x.put (X, new HashSet<Integer> ());
			}

			if (!y.containsKey (Y)) {
				y.put (Y, new HashSet<Integer> ());
			}

			x.get (X).add (Y);
			y.get (Y).add (X);
		}

		for (int q = 0; q < Q; q++) {
			t = in.readLine ().split (" ");

			if (t[0].charAt (0) == '1') {
				X = Integer.parseInt (t[1]);
				Y = Integer.parseInt (t[2]);
				System.out.println (x.containsKey (X) && x.get (X).contains (Y) ? "salty" : "bland");
			}
			else if (t[0].charAt (0) == '2' && t[1].charAt (0) == 'X') {
				X = Integer.parseInt (t[2]);
				System.out.println (x.containsKey (X) ? x.get (X).size () : 0);
			}
			else {
				Y = Integer.parseInt (t[2]);
				System.out.println (y.containsKey (Y) ? y.get (Y).size () : 0);
			}
		}
	}
}