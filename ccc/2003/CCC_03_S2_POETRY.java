import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_03_S2_POETRY {

	public static String syl (String a) {
		a = a.toLowerCase ();
		StringBuilder s1 = new StringBuilder (a.length ());

		for (int i = a.length () - 1; i >= 0; i--) {
			s1.insert (0, a.charAt (i));

			if (!(a.charAt (i) != 'a' && a.charAt (i) != 'e' && a.charAt (i) != 'i' && a.charAt (i) != 'o' && a.charAt
					(i) != 'u' && a.charAt (i) != ' ')) {
				break;
			}
		}

		return s1.toString ().trim ();
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] t = new String[4];
		String[] s = new String[4];

		for (int x = 0; x < N; x++) {
			for (int i = 0; i < 4; i++) {
				t[i] = in.readLine ();
				s[i] = syl (t[i]);
				//System.out.println (s [i]);
			}

			if (s[0].equals (s[1]) && s[1].equals (s[2]) && s[2].equals (s[3])) {
				System.out.println ("perfect");
			}
			else if (s[0].equals (s[1]) && s[2].equals (s[3]) && !s[0].equals (s[3])) {
				System.out.println ("even");
			}
			else if (s[0].equals (s[2]) && s[1].equals (s[3]) && !s[0].equals (s[1])) {
				System.out.println ("cross");
			}
			else if (s[0].equals (s[3]) && s[1].equals (s[2]) && !s[0].equals (s[1])) {
				System.out.println ("shell");
			}
			else {
				System.out.println ("free");
			}
		}
	}
}