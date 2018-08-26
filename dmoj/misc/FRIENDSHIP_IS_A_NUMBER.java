import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableMap;
import java.util.TreeMap;

// atharva washimkar
// August 11, 2018

public class FRIENDSHIP_IS_A_NUMBER {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		NavigableMap<Integer, Integer> map = new TreeMap<Integer, Integer> ();
		int N = Integer.parseInt (in.readLine ());

		for (int i = 0; i < N; i++) {
			String[] t = in.readLine ().split (" ");
			int p = 1;

			for (int j = 1; j < t.length; j++) {
				p *= Integer.parseInt (t[j]);
			}
			map.put (p, i + 1);
		}

		for (int i = 0; i < 3; i++) {
			System.out.println (map.get (map.lastKey ()));
			map.remove (map.lastKey ());
		}
	}
}