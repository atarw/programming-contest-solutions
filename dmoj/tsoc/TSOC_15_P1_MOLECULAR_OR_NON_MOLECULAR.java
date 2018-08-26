import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// atharva washimkar
// August 11, 2018

public class TSOC_15_P1_MOLECULAR_OR_NON_MOLECULAR {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Set<String> set = new HashSet<String> ();

		set.add ("Cl");
		set.add ("Br");
		set.add ("Xe");
		set.add ("Kr");
		set.add ("Si");
		set.add ("As");
		set.add ("Rn");
		set.add ("Ne");
		set.add ("He");
		set.add ("H");
		set.add ("C");
		set.add ("N");
		set.add ("O");
		set.add ("F");
		set.add ("P");
		set.add ("S");
		set.add ("I");

		int N = Integer.parseInt (in.readLine ());
		String[] t;
		boolean good = true;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");

			for (String i : t) {
				if (!set.contains (i)) {
					good = false;
					break;
				}
			}

			if (good) System.out.println ("Molecular!");
			else System.out.println ("Not molecular!");

			good = true;
		}
	}
}