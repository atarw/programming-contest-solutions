import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// atharva washimkar
// August 11, 2018

public class MACKENZIE_NEW_YEARS_CHALLENGE_P4_ABDOOF {

	public static List<String> split (String i, Set<String> F) {
		List<String> list = new LinkedList<String> ();

		//System.out.println ("'" + i + "'");

		if (!i.isEmpty () && !i.equals ("")) {
			for (String x : F) {
				if (i.startsWith (x)) {
					if (x.equals ("b") && i.startsWith ("bd")) {//FUCKING JUSTIN AND HIS SECTIONS HAVING SIMILAR
						// BEGINNINGS
						x = "bd";
					}

					list.add (x);
					//System.out.println (list);
					i = i.substring (x.length ());

					List<String> add = split (i, F);
					//System.out.println ("A: " + add);

					if (add != null)
						list.addAll (add);
					else
						return null;
					break;
				}
			}

			//if (list.size () < 2)
			// return null;
		}

		return list;
	}

	public static String sw (Set<String> s, String i) {
		for (String x : s) {
			if (i.startsWith (x)) {
				return x;
			}
		}
		return null;
	}

	public static String ew (Set<String> s, String i) {
		for (String x : s) {
			if (i.endsWith (x)) {
				return x;
			}
		}
		return null;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" +");
		Set<String> A = new HashSet<String> ();
		Set<String> B = new HashSet<String> ();
		Set<String> C = new HashSet<String> ();
		Set<String> F = new HashSet<String> ();

		A.add ("ch");
		A.add ("t");
		A.add ("m");
		A.add ("d");
		A.add ("b");
		A.add ("bd");
		A.add ("r");

		B.add ("a");
		B.add ("e");
		B.add ("i");
		B.add ("oo");

		C.add ("n");
		C.add ("v");
		C.add ("f");

		F.addAll (A);
		F.addAll (B);
		F.addAll (C);

		boolean g = true;

		List<String> list;
		String last = "";
		String curr = "";

		// < 2 SECTIONS
		if (t.length > 1) {
			out:
			for (String i : t) {
				list = split (i.toLowerCase (), F);

				if (list == null) {
					//System.out.println ("null");
					g = false;
					break out;
				}
				else {
					list = new ArrayList<String> (list);
					// System.out.println (list);
				}

				for (int x = 0; x < list.size (); x++) {
					curr = A.contains (list.get (x)) ? "A" : B.contains (list.get (x)) ? "B" : "C";

					//System.out.println (curr);

					if (x == 0) {
						if (!curr.equals ("A") && !curr.equals ("B")) {
							g = false;
							break out;
						}
						else {
							last = curr;
						}
					}
					else if (x == list.size () - 1) {
						if (!curr.equals ("A") && !curr.equals ("C")) {
							g = false;
							break out;
						}
						else {
							last = curr;
						}
					}
					else {
						if (curr.equals ("C")) {
							g = false;
							break out;
						}
						else if (last.equals (curr)) {
							g = false;
							break out;
						}
						last = curr;
					}
				}
			}
		}
		else {
			g = false;
		}

		if (g) System.out.println ("valid");
		else System.out.println ("invalid");
	}
}