import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class INTERLEAVE {

	public static boolean valid (String a, String b, String c) {

		int index1 = 0, index2 = 0;

		for (int i = 0; i < c.length (); i++) {
			System.out.println (c.charAt (i) + " - " + index1 + " " + index2);

			if (index1 < a.length () && c.charAt (i) == a.charAt (index1)) {
				index1++;
			}
			else if (index2 < b.length () && c.charAt (i) == b.charAt (index2)) {
				index2++;
			}
			else {
				return false;
			}
		}

		return true;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String a = in.readLine (), b = in.readLine (), c = in.readLine ();

		System.out.println (valid (a, b, c));
	}
}