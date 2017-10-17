import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// Oct 07, 2017

public class TLE_17_P1_STRING_ROUNDING {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		char[] s = in.readLine ().toCharArray ();
		boolean addA = false;

		for (int i = s.length - 1; i >= 0; --i) {
			if (s[i] <= 'm') {
				s[i] = 'a';
				break;
			}
			else {
				s[i] = 'a';

				if (i == 0) {
					addA = true;
					break;
				}
				else {
					if (s[i - 1] == 'z') {
						continue;
					}
					else {
						s[i - 1] = (char) (s[i - 1] + 1);
						break;
					}
				}
			}
		}

		if (addA)
			out.print ('a');

		for (int i = 0; i < s.length; ++i)
			out.print (s[i]);

		out.close ();
	}
}