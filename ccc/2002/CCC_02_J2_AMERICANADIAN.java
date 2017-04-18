import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CCC_02_J2_AMERICANADIAN {

	static Set <Character> vowels = new HashSet <Character> ();

	public static String spell (String input) {


		if (input.length () > 4 && input.endsWith ("or")) {
			int index = input.lastIndexOf ("or");

			if (!vowels.contains ((input.charAt (index - 1) + "").toLowerCase ().charAt (0))) {
				input = input.substring (0, index) + "our";
			}
		}
		return input;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String input = in.readLine ();

		vowels.add ('a');
		vowels.add ('e');
		vowels.add ('i');
		vowels.add ('o');
		vowels.add ('u');
		vowels.add ('y');

		while (!input.equals ("quit!")) {
			System.out.println (spell (input));

			input = in.readLine ();
		}
	}
}