import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// atharva washimkar
// August 11, 2018

public class CCC_11_J4_BORING_BUSINESS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Set<P> set = new HashSet<P> ();

		String ln;
		String[] t;

		P curr = new P (-1, -5);
		set.add (curr);

		set.add (new P (0, -1));
		set.add (new P (0, -2));
		set.add (new P (0, -3));

		set.add (new P (1, -3));
		set.add (new P (2, -3));
		set.add (new P (3, -3));

		set.add (new P (3, -3));
		set.add (new P (3, -4));
		set.add (new P (3, -5));

		set.add (new P (4, -5));
		set.add (new P (5, -5));

		set.add (new P (5, -4));
		set.add (new P (5, -3));

		set.add (new P (6, -3));
		set.add (new P (7, -3));

		set.add (new P (7, -4));
		set.add (new P (7, -5));
		set.add (new P (7, -6));
		set.add (new P (7, -7));

		set.add (new P (6, -7));
		set.add (new P (5, -7));
		set.add (new P (4, -7));
		set.add (new P (3, -7));
		set.add (new P (2, -7));
		set.add (new P (1, -7));
		set.add (new P (0, -7));
		set.add (new P (-1, -7));

		set.add (new P (-1, -6));

		int val = 0;
		boolean good = true;

		while ((ln = in.readLine ()).charAt (0) != 'q') {
			t = ln.split (" ");
			val = Integer.parseInt (t[1]);

			if (t[0].equals ("l")) {
				for (int x = curr.X - 1; x >= curr.X - val; x--) {
					if (!set.add (new P (x, curr.Y))) {
						good = false;
						break;
					}
				}
				curr = new P (curr.X - val, curr.Y);
			}
			else if (t[0].equals ("d")) {
				for (int y = curr.Y - 1; y >= curr.Y - val; y--) {
					if (y == -7) {
					}

					if (!set.add (new P (curr.X, y))) {
						good = false;
						break;
					}
				}
				curr = new P (curr.X, curr.Y - val);
			}
			else if (t[0].equals ("r")) {
				for (int x = curr.X + 1; x <= curr.X + val; x++) {
					if (!set.add (new P (x, curr.Y))) {
						good = false;
						break;
					}
				}
				curr = new P (curr.X + val, curr.Y);
			}
			else if (t[0].equals ("u")) {
				for (int y = curr.Y + 1; y <= curr.Y + val; y++) {
					if (!set.add (new P (curr.X, y))) {
						good = false;
						break;
					}
				}
				curr = new P (curr.X, curr.Y + val);
			}

			if (!good) {
				System.out.println (curr + " DANGER");
				break;
			}
			else {
				System.out.println (curr + " safe");
				good = true;
			}
		}
	}

	private static class P {

		int X, Y;

		public P (int X, int Y) {
			this.X = X;
			this.Y = Y;
		}

		public String toString () {
			return X + " " + Y;
		}

		public boolean equals (Object o) {
			P p = ((P) o);

			return p.X == X && p.Y == Y;
		}

		public int hashCode () {
			return X * 17 + Y * 37;
		}
	}
}