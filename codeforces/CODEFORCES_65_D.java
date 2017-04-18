import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class CODEFORCES_65_D {

	static boolean G, S, H, R;// booleans indicating whether it is possible to be sorted into the house
	static String ln;

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		in.readLine ();
		ln = in.readLine ();

		Queue <State> queue = new ArrayDeque <State> ();
		Set <State> vis = new HashSet <State> ();
		State q = new State (0, 0, 0, 0, 0);

		queue.offer (q);

		// BFS through the decision tree
		while (!queue.isEmpty () && (!G || !S || !H || !R)) {
			if (vis.add (q = queue.poll ())) {
				if (q.N == ln.length ()) {// end of string: check which houses are available
					if (q.G <= q.S && q.G <= q.H && q.G <= q.R) {
						G = true;
					}
					if (q.S <= q.G && q.S <= q.H && q.S <= q.R) {
						S = true;
					}
					if (q.H <= q.S && q.H <= q.G && q.H <= q.R) {
						H = true;
					}
					if (q.R <= q.S && q.R <= q.H && q.R <= q.G) {
						R = true;
					}
				}
				else if (ln.charAt (q.N) == 'G') {
					queue.offer (new State (q.N + 1, q.G + 1, q.S, q.H, q.R));
				}
				else if (ln.charAt (q.N) == 'H') {
					queue.offer (new State (q.N + 1, q.G, q.S, q.H + 1, q.R));
				}
				else if (ln.charAt (q.N) == 'R') {
					queue.offer (new State (q.N + 1, q.G, q.S, q.H, q.R + 1));
				}
				else if (ln.charAt (q.N) == 'S') {
					queue.offer (new State (q.N + 1, q.G, q.S + 1, q.H, q.R));
				}
				else if (ln.charAt (q.N) == '?') {
					if (q.G <= q.S && q.G <= q.H && q.G <= q.R) {
						queue.offer (new State (q.N + 1, q.G + 1, q.S, q.H, q.R));
					}

					if (q.S <= q.G && q.S <= q.H && q.S <= q.R) {
						queue.offer (new State (q.N + 1, q.G, q.S + 1, q.H, q.R));
					}

					if (q.H <= q.S && q.H <= q.G && q.H <= q.R) {
						queue.offer (new State (q.N + 1, q.G, q.S, q.H + 1, q.R));
					}

					if (q.R <= q.S && q.R <= q.H && q.R <= q.G) {
						queue.offer (new State (q.N + 1, q.G, q.S, q.H, q.R + 1));
					}
				}
			}
		}

		if (G) {
			System.out.println ("Gryffindor");
		}
		if (H) {
			System.out.println ("Hufflepuff");
		}
		if (R) {
			System.out.println ("Ravenclaw");
		}
		if (S) {
			System.out.println ("Slytherin");
		}
	}

	//N -> position in the string, G -> #ppl sorted into griffindor, S -> #ppl sorted into slytherin, and so forth for
	// hufflepuff and ravenclaw
	private static class State {

		int N, G, S, H, R;

		public State (int N, int G, int S, int H, int R) {
			this.N = N;
			this.G = G;
			this.S = S;
			this.H = H;
			this.R = R;
		}

		public int hashCode () {
			return N * 13 + G * 19 + S * 23 + H * 31 + R * 47;
		}

		public boolean equals (Object o) {
			State q = (State) o;
			return q.N == N && q.G == G && q.S == S && q.H == H && q.R == R;
		}
	}
}