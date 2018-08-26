import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CCC_07_J3_DEAL_OR_NO_DEAL_CALCULATOR {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
		int N = Integer.parseInt (in.readLine ());

		map.put (1, 100);
		map.put (2, 500);
		map.put (3, 1000);
		map.put (4, 5000);
		map.put (5, 10000);
		map.put (6, 25000);
		map.put (7, 50000);
		map.put (8, 100000);
		map.put (9, 500000);
		map.put (10, 1000000);

		for (int i = 0; i < N; i++) {
			map.remove (Integer.parseInt (in.readLine ()));
		}

		double average = 0;

		for (Integer i : map.values ()) {
			average += i;
		}

		average /= map.size ();

		int A = Integer.parseInt (in.readLine ());

		if (A > average) {
			System.out.println ("deal");
		}
		else {
			System.out.println ("no deal");
		}
	}
}