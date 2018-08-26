import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TLE_16_P1_ENGLISH_PROFIENCY_EXAM {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		boolean read = true;

		for (int n = 0; n < t.length; ++n) {
			int v = 0, c = 0;

			if (t[n].length () == 1 && !(t[n].charAt (0) == 'a' || t[n].charAt (0) == 'e' || t[n].charAt (0) == 'i' ||
					t[n].charAt (0) == 'o' || t[n].charAt (0) == 'u')) {
				read = false;
				break;
			}

			for (int i = 0; i < t[n].length (); ++i) {
				if (t[n].charAt (i) == 'a' || t[n].charAt (i) == 'e' || t[n].charAt (i) == 'i' || t[n].charAt (i) ==
						'o' || t[n].charAt (i) == 'u')
					++v;
				else
					++c;
			}

			if (!(v == c || v + 1 == c || c + 1 == v)) {
				read = false;
				break;
			}
		}

		out.println ((read ? "" : "not ") + "readable");
		out.close ();
	}
}