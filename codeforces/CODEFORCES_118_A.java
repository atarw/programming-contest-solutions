import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CODEFORCES_118_A {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		String ln = in.readLine ().toLowerCase ();
		char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};

		for (char c : vowels) {
			ln = ln.replaceAll (c + "", "");
		}

		for (int i = 0; i < ln.length (); i++) {
			out.print ('.');
			out.print (ln.charAt (i));
		}

		out.close ();
	}
}