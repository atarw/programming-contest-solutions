import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// atharva washimkar
// August 11, 2018

public class CCC_15_J2_HAPPY_OR_SAD {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String s = in.readLine ();

		int sCount = 0, hCount = 0;
		Pattern sf = Pattern.compile (":-\\(");
		Pattern hf = Pattern.compile (":-\\)");
		Matcher matcher = sf.matcher (s);

		while (matcher.find ()) {
			sCount++;
		}

		matcher = hf.matcher (s);

		while (matcher.find ()) {
			hCount++;
		}

		if (hCount == sCount) {
			if (hCount == 0) {
				System.out.println ("none");
			}
			else {
				System.out.println ("unsure");
			}
		}
		else if (sCount > hCount) {
			System.out.println ("sad");
		}
		else {
			System.out.println ("happy");
		}
	}
}