import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class CCC_15_J4_WAIT_TIME {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Map<Integer, Integer> received = new TreeMap<Integer, Integer> ();
		Map<Integer, Integer> sent = new TreeMap<Integer, Integer> ();
		Map<Integer, Integer> total = new TreeMap<Integer, Integer> ();

		int M = Integer.parseInt (in.readLine ());
		String[] tokens;

		for (int i = 0; i < M; i++) {
			tokens = in.readLine ().split (" ");
			int pot = Integer.parseInt (tokens[1]);


			if (!tokens[0].equals ("W")) {
				for (Integer x : total.keySet ()) {
					if (sent.get (x) == -1) {
						total.put (x, total.get (x) + 1);
					}
				}
			}

			if (tokens[0].equals ("R")) {
				received.put (pot, i);
				sent.put (pot, -1);

				if (!total.containsKey (pot)) {
					total.put (pot, 0);
				}
			}
			else if (tokens[0].equals ("S")) {
				sent.put (pot, i);
				//total.put (pot, total.get (pot) + (sent.get (pot) - received.get (pot)));
			}
			else {
				for (Integer x : total.keySet ()) {
					if (sent.get (x) == -1) {
						total.put (x, total.get (x) + pot - 1);
					}
				}
			}

			//System.out.println (i + " " + tokens [0] + " " + tokens [1] + "\n" + received + "\n" + sent + "\n" +
			// total + "\n");
		}

		for (Integer i : sent.keySet ()) {
			System.out.print (i + " ");
			if (sent.get (i) == -1) {
				System.out.print (-1);
			}
			else {
				System.out.print (total.get (i));
			}
			System.out.println ();
		}
	}
}