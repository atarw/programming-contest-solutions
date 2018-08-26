import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// atharva washimkar
// August 11, 2018

public class CCC_15_J3_ROVARSPRAKET {

	static final Set<Character> vowels = new HashSet<Character> ();

	public static char nextCons (char c) {
		char cons = c;

		while (cons != 'z') {
			cons = (char) (cons + 1);

			if (!vowels.contains (cons)) {
				break;
			}
		}

		return cons;
	}

	public static char[] range (char c) {
		if ('a' < c && c < 'e') {
			return new char[]{'a', 'e'};
		}
		else if ('e' < c && c < 'i') {
			return new char[]{'e', 'i'};
		}
		else if ('i' < c && c < 'o') {
			return new char[]{'i', 'o'};
		}
		else if ('o' < c && c < 'u') {
			return new char[]{'o', 'u'};
		}
		else {
			return new char[]{'u'};
		}
	}

	public static char closestVow (char c) {
		char[] range = range (c);

		if (range.length == 1) {
			return 'u';
		}
		else {
			int left = Math.abs (c - range[0]);
			int right = Math.abs (c - range[1]);

			if (left <= right) {
				return range[0];
			}
			else {
				return range[1];
			}
		}
	}

	public static String conv (String t) {
		StringBuilder b = new StringBuilder ();

		for (int i = 0; i < t.length (); i++) {
			b.append (t.charAt (i));

			if (!vowels.contains (t.charAt (i))) {
				char[] txt = {closestVow (t.charAt (i)), nextCons (t.charAt (i))};
				b.append (txt);
			}
		}

		return b.toString ();
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String txt = in.readLine ();

		vowels.add ('a');
		vowels.add ('e');
		vowels.add ('i');
		vowels.add ('o');
		vowels.add ('u');

		System.out.println (conv (txt));
	}
}