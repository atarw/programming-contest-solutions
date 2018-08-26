import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

// atharva washimkar
// August 11, 2018

public class CCC_07_J5_KEEP_ON_TRUCKING {

	public static final NavigableSet<Integer> set = new TreeSet<Integer> ();
	public static int A, B;
	public static Map<Integer, Integer> map = new HashMap<Integer, Integer> ();

  /*public static String indent (int depth) {
   StringBuilder s = new StringBuilder (depth);

   for (int i = 0; i < depth; i++) {
   s.append (" ");
   }
   return s.toString ();
   }*/

	public static int ways (Integer loc, int depth) {
		if (loc == set.last ()) {//reached end
			//System.out.println ("reached end");

			map.put (loc, map.get (loc) + 1);
		}
		else {
			NavigableSet<Integer> sub = set.subSet (loc + A, true, loc + B, true);
			if (sub.isEmpty ()) {//no hotels found between min and max distance travelled
				//System.out.println ("no hotels between " + (loc + A) + "-" + (loc + B));
			}
			else {
				int total = 0;

				for (Integer i : sub) {
					total += ways (i, depth + 1);
				}
				map.put (loc, total);
			}
		}

		return map.get (set.last ());
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		A = Integer.parseInt (in.readLine ());
		B = Integer.parseInt (in.readLine ());
		int N = Integer.parseInt (in.readLine ());

		set.add (0);
		set.add (990);
		set.add (1010);
		set.add (1970);
		set.add (2030);
		set.add (2940);
		set.add (3060);
		set.add (3930);
		set.add (4060);
		set.add (4970);
		set.add (5030);
		set.add (5990);
		set.add (6010);
		set.add (7000);

		for (int i = 0; i < N; i++) {
			set.add (Integer.parseInt (in.readLine ()));
		}

		for (Integer i : set) {
			map.put (i, 0);
		}

		System.out.println (ways (0, 0));
	}
}