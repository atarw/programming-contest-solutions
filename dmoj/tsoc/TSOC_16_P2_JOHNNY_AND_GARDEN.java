import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class TSOC_16_P2_JOHNNY_AND_GARDEN {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String txt = in.readLine ();

		if (!txt.contains (".")) {
			String txt2 = in.readLine ();

			System.out.println ("\"" + txt + "\" - " + txt2.toLowerCase ());
		}
		else {
			int a = txt.indexOf (".");
			System.out.println ("\"" + txt.substring (0, a) + "\" - " + txt.substring (a + 1).toLowerCase ());
		}
	}
}