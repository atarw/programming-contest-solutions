import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class ENCODE {

	public static char shift (char c, int N) {
		if (Character.isUpperCase (c)) {
			if ('A' > c - N) {
				return (char) (c + (26 - N));
			}
			else {
				return (char) (c - N);
			}
		}
		else {
			if ('a' > c - N) {
				return (char) (c + (26 - N));
			}
			else {
				return (char) (c - N);
			}
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = (Integer.parseInt (in.readLine ()) % 26);
		if (N < 0) N += 26;

		String s = in.readLine ();

		for (int i = 0; i < s.length (); i++) {
			if (s.charAt (i) != ' ') {
				System.out.print (shift (s.charAt (i), N));
			}
			else {
				System.out.print (' ');
			}
		}
	}
}