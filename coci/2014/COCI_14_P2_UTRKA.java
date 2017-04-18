import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class COCI_14_P2_UTRKA {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		Map <String, Integer> set = new HashMap <String, Integer> ();
		String ln;

		for (int i = 0; i < N; i++) {
			ln = in.readLine ();

			if (set.containsKey (ln)) {
				set.put (ln, set.get (ln) + 1);
			}
			else {
				set.put (ln, 1);
			}
		}

		int diff;

		for (int i = 0; i < N - 1; i++) {
			ln = in.readLine ();
			diff = set.get (ln) - 1;

			if (diff == 0) {
				set.remove (ln);
			}
			else {
				set.put (ln, diff);
			}
		}

		System.out.println (set.keySet ().toArray ()[0]);
	}
}