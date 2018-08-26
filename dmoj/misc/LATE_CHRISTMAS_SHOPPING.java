import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// atharva washimkar
// August 11, 2018

public class LATE_CHRISTMAS_SHOPPING {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] t;

		Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
		boolean g = true;

		out:
		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");

			for (int m = 1; m < t.length; m++) {
				if (map.containsKey (Integer.parseInt (t[m])) && map.get (Integer.parseInt (t[m])) != n) {
					g = false;
					break out;
				}
				else {
					map.put (Integer.parseInt (t[m]), n);
				}
			}
		}

		System.out.println (g ? "NO" : "YES");
	}
}