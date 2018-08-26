import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;

// atharva washimkar
// August 11, 2018

public class COCI_13_P4_LOPOV {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), K = Integer.parseInt (t[1]);

		Jewellery[] arr = new Jewellery[N];
		NavigableMap<Integer, Integer> bags = new TreeMap<Integer, Integer> ();

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			arr[n] = new Jewellery (Integer.parseInt (t[0]), Integer.parseInt (t[1]));
		}

		int V;

		for (int k = 0; k < K; k++) {
			V = Integer.parseInt (in.readLine ());

			if (!bags.containsKey (V)) {
				bags.put (V, 1);
			}
			else {
				bags.put (V, bags.get (V) + 1);
			}
		}

		Arrays.sort (arr);
		long total = 0L;
		Integer a;

		for (int n = 0; n < N && !bags.isEmpty (); n++) {
			a = bags.ceilingKey (arr[n].M);

			if (a != null) {
				total += arr[n].V;

				if (bags.get (a) == 1) {
					bags.remove (a);
				}
				else {
					bags.put (a, bags.get (a) - 1);
				}
			}
		}

		System.out.print (total);
	}

	private static class Jewellery implements Comparable<Jewellery> {

		int M, V;

		public Jewellery (int M, int V) {
			this.M = M;
			this.V = V;
		}

		public int compareTo (Jewellery j) {
			return j.V - V;
		}
	}
}