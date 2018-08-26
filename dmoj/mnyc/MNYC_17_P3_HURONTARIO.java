import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MNYC_17_P3_HURONTARIO {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");

		String s1 = t[0], s2 = t[1];
		int m = 0;

		for (int i = 0, j = 0; i < s1.length () && j < s2.length (); ) {
			if (i + s2.length () < s1.length ()) {
				++i;
				continue;
			}

			if (s1.charAt (i) == s2.charAt (j)) {
				while (s1.charAt (i) == s2.charAt (j)) {
					++i;
					++j;
					++m;

					if (i == s1.length () || j == s2.length ())
						break;
				}
			}
			else {
				++i;

				if (i == s1.length ()) {
					m = 0;
				}
			}
		}

		out.print (s1);

		if (m == 0) {
			out.print (s2);
		}
		else {
			for (int k = m; k < s2.length (); ++k)
				out.print (s2.charAt (k));
		}

		out.close ();
	}
}