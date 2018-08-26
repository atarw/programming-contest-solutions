import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// atharva washimkar
// August 11, 2018

public class VMSS_15_P1_ALEXS_BAE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String a = in.readLine ().toLowerCase (), b = in.readLine ().toLowerCase ();
		Map<Character, Integer> map = new HashMap<Character, Integer> ();
		boolean no = false;

		for (int i = 0; i < a.length (); i++) {
			if (a.charAt (i) != ' ') {
				if (map.containsKey (a.charAt (i))) {
					map.put (a.charAt (i), map.get (a.charAt (i)) + 1);
				}
				else {
					map.put (a.charAt (i), 1);
				}
			}
		}

		for (int i = 0; i < b.length (); i++) {
			if (b.charAt (i) != ' ') {
				if (map.containsKey (b.charAt (i))) {
					map.put (b.charAt (i), map.get (b.charAt (i)) - 1);

					if (map.get (b.charAt (i)) < 0) {
						no = true;
						System.out.println ("no");
						break;
					}
				}
				else {
					no = true;
					System.out.println ("no");
					break;
				}
			}
		}

		if (!no) {
			System.out.println ("yes");
		}
	}
}