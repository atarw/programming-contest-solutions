import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// atharva washimkar
// August 11, 2018

public class CCC_06_S2_ATTACK_OF_THE_CIPHERTEXTS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Map<Character, Character> map = new HashMap<Character, Character> ();

		String plain = in.readLine ();
		String cip1 = in.readLine ();
		String cip2 = in.readLine ();

		for (int i = 0; i < plain.length (); i++) {
			map.put (cip1.charAt (i), plain.charAt (i));
		}

		for (int i = 0; i < cip2.length (); i++) {
			if (map.containsKey (cip2.charAt (i))) {
				System.out.print (map.get (cip2.charAt (i)));
			}
			else {
				System.out.print (".");
			}
		}
	}
}