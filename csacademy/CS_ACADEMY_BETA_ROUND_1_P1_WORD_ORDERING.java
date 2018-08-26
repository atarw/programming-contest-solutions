import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CS_ACADEMY_BETA_ROUND_1_P1_WORD_ORDERING {

	static String order;

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		List<String> list = new ArrayList<String> ();
		order = in.readLine ();
		order += order.toUpperCase ();

		int N = Integer.parseInt (in.readLine ());

		for (int n = 0; n < N; n++) {
			list.add (in.readLine ());
		}

		Collections.sort (list, new Comparator<String> () {
			public int compare (String o1, String o2) {
				int pos1, pos2;

				for (int i = 0; i < Math.min (o1.length (), o2.length ()); i++) {
					pos1 = order.indexOf (o1.charAt (i));
					pos2 = order.indexOf (o2.charAt (i));

					if (pos1 > pos2) {
						return 1;
					}
					else if (pos1 < pos2) {
						return -1;
					}
				}

				if (o1.length () > o2.length ()) {
					return 1;
				}
				else if (o1.length () < o2.length ()) {
					return -1;
				}
				else {
					return 0;
				}
			}
		});

		for (String i : list) {
			out.println (i);
		}

		out.close ();
	}
}