import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// atharva washimkar
// August 11, 2018

public class MACKENZIE_NEW_YEARS_CHALLENGE_P6_ROCKS {

	public static int mass (String n) {
		int mass = 0;

		for (int i = 0; i < n.length (); i++) {
			mass += (n.charAt (i) - '`');
		}

		return mass;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t;
		int C = Integer.parseInt (in.readLine ());

		Map<String, Integer> map = new HashMap<String, Integer> ();//string to index
		PrefixSumArray arr = new PrefixSumArray ();

		for (int c = 0; c < C; c++) {
			t = in.readLine ().split (" ");

			if (t[0].equals ("A")) {
				if (map.containsKey (t[1])) {
					System.out.println ("Can't add " + t[1]);
				}
				else {
					map.put (t[1], map.size () + 1);
					arr.insert (mass (t[1]));
				}
			}
			else if (t[0].equals ("S")) {
				int pos = map.get (t[1]);

				arr.swap (pos, map.get (t[2]));

				map.put (t[1], map.get (t[2]));
				map.put (t[2], pos);
			}
			else if (t[0].equals ("M")) {
				System.out.println (arr.sum (Math.min (map.get (t[1]), map.get (t[2])), Math.max (map.get (t[2]), map
						.get (t[1]))));
			}
			else if (t[0].equals ("R")) {
				int pos = map.get (t[1]);
				map.remove (t[1]);
				map.put (t[2], pos);

				arr.update (pos, mass (t[2]));
			}
			else if (t[0].equals ("N")) {
				System.out.println (map.size ());
			}

			//System.out.println (t [0] + " " + arr);
		}
	}

	private static class PrefixSumArray {

		int size = 1;
		private int[] arr = new int[10002];
		private int[] vals = new int[10002];//not 1-indexed

		public PrefixSumArray () {}

		public void swap (int index1, int index2) {
			int val = vals[index1 - 1];

			vals[index1 - 1] = vals[index2 - 1];
			vals[index2 - 1] = val;

			build (Math.min (index1, index2), Math.max (index1, index2));
		}

		public void insert (int val) {
			vals[size - 1] = val;
			arr[size] = arr[size - 1] + val;
			size++;
		}

		public int sum (int to) {
			return sum (0, to);
		}

		public int sum (int from, int to) {
			return arr[to] - arr[from - 1];
		}

		public void update (int index, int val) {
			vals[index - 1] = val;
			build (index);
		}

		private void build (int from) {
			build (from, size);
		}

		private void build (int from, int to) {
			for (int i = from; i < to; i++) {
				arr[i] = arr[i - 1] + vals[i - 1];
			}
		}

		public String toString () {
			StringBuilder b = new StringBuilder ();

			b.append ("VALS: ");

			for (int i = 0; i < size + 10; i++) {
				b.append (vals[i] + " ");
			}

			b.append ("\nSUMS: ");

			for (int i = 0; i <= size + 10; i++) {
				b.append (arr[i] + " ");
			}
			b.append ("\n");

			return b.toString ();
		}
	}
}