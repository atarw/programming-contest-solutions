import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableSet;
import java.util.TreeSet;

// atharva washimkar
// August 11, 2018

public class CCO_2013_P1_ALL_YOUR_BASE_BELONG_TO_PALINDROMES {

	public static String convert (int num, int base) {
		StringBuilder a = new StringBuilder ();
		int diff;
		int q = num;

		while (q != 0) {
			q = q / base;
			diff = num - base * q;
			num = q;

			a.insert (0, getDigit (diff));
		}

		return a.toString ();
	}

	public static char getDigit (int remainder) {
		if (remainder > 9) {
			return (char) (55 + remainder);
		}
		else {
			return Character.forDigit (remainder, 10);
		}
	}

	public static boolean palindrome (String s) {
		for (int i = 0; i < s.length (); i++) {
			if (s.charAt (i) != s.charAt (s.length () - i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		NavigableSet<Integer> set = new TreeSet<Integer> ();
		int T = Integer.parseInt (in.readLine ());

		for (int i = 1; i * i + i < T; i++) {
			if (T % i == 0) {
				set.add (T / i - 1);
			}
		}

		for (int i = 2; i * i < T; i++) {
			if (palindrome (convert (T, i))) {
				set.add (i);
			}
		}

		for (int i : set) {
			System.out.println (i);
		}
	}
}