import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// atharva washimkar
// August 11, 2018

public class DMOPC_13_P1_SHARING_IS_CARING {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), B;
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>> ();

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			B = Integer.parseInt (t[1]);

			if (!map.containsKey (B)) {
				map.put (B, new ArrayList<String> ());
			}

			map.get (B).add (in.readLine ());
		}

		int X = Integer.parseInt (in.readLine ());

		if (map.containsKey (X)) {
			for (String i : map.get (X)) {
				System.out.println (i);
			}
		}
	}
}