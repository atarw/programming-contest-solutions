import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P3_SAWMILL {

	public static List<Integer> get (String input) {
		String[] tokens = (input).split (" ");
		List<Integer> S = new LinkedList<Integer> ();

		for (int i = 0; i < tokens.length; i++) {
			S.add (Integer.parseInt (tokens[i]));
		}

		return S;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		List<Integer> S = get (in.readLine ());
		List<Integer> L = get (in.readLine ());

		Collections.sort (S);
		Collections.sort (L, Collections.reverseOrder ());

		long product = 0;

		while (!S.isEmpty ()) {
			product += S.get (0) * L.get (0);
			S.remove (0);
			L.remove (0);
		}

		System.out.println (product);
	}
}