import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_05_S1_SNOW_CALLS {

	public static char conv (char ch) {
		if (ch == 'A' || ch == 'B' || ch == 'C') {
			return '2';
		}
		else if (ch == 'D' || ch == 'E' || ch == 'F') {
			return '3';
		}
		else if (ch == 'G' || ch == 'H' || ch == 'I') {
			return '4';
		}
		else if (ch == 'J' || ch == 'K' || ch == 'L') {
			return '5';
		}
		else if (ch == 'M' || ch == 'N' || ch == 'O') {
			return '6';
		}
		else if (ch == 'P' || ch == 'Q' || ch == 'R' || ch == 'S') {
			return '7';
		}
		else if (ch == 'T' || ch == 'U' || ch == 'V') {
			return '8';
		}
		else if (ch == 'W' || ch == 'X' || ch == 'Y' || ch == 'Z') {
			return '9';
		}
		return '0';
	}

	public static String conv (String num) {
		num = num.replaceAll ("-", "");
		StringBuilder newNum = new StringBuilder (num.length () + 3);

		for (int i = 0; i < 10; i++) {
			if (Character.isDigit (num.charAt (i))) {
				newNum.append (num.charAt (i));
			}
			else if (Character.isLetter (num.charAt (i))) {
				newNum.append (conv (num.charAt (i)));
			}
		}
		newNum.insert (3, '-');
		newNum.insert (7, '-');

		return newNum.toString ();
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int T = Integer.parseInt (in.readLine ());

		for (int i = 0; i < T; i++) {
			System.out.println (conv (in.readLine ()));
		}
	}
}