import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P3_GLOBALLY_UNIQUE_SHELLS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Set<Integer> set = new HashSet<Integer> ();
		int N = Integer.parseInt (in.readLine ());

		String[] t = in.readLine ().split (" ");
		int A = Integer.parseInt (t[0]), B = Integer.parseInt (t[1]);
		int S = 0;

		t = in.readLine ().split (" ");

		for (String i : t) {
			set.add (Integer.parseInt (i));
			S++;
		}

		t = in.readLine ().split (" ");

		for (String i : t) {
			int C = Integer.parseInt (i);
			if (!set.add (C)) {
				set.remove (C);
			}
			else {
				S++;
			}
		}

		System.out.println (N - S + set.size ());
	}
}