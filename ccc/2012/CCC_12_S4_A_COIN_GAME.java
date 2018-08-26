import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// atharva washimkar
// August 11, 2018

public class CCC_12_S4_A_COIN_GAME {

	static int N;

	public static List<State> moves (State s) {
		List<State> moves = new ArrayList<State> ();
		State c;

		for (int n = N - 1; n >= 0; --n) {
			if (s.row[n].isEmpty ())
				continue;

			if (n != N - 1 && (s.row[n + 1].isEmpty () || s.row[n].peek () < s.row[n + 1].peek ())) {
				c = new State (s);

				c.row[n + 1].push (c.row[n].pop ());
				moves.add (c);
			}

			if (n != 0 && (s.row[n - 1].isEmpty () || s.row[n].peek () < s.row[n - 1].peek ())) {
				c = new State (s);

				c.row[n - 1].push (c.row[n].pop ());
				moves.add (c);
			}
		}

		return moves;
	}

	public static void main (String[] t) throws IOException {
		//long a = System.currentTimeMillis ();
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		while (true) {
			N = Integer.parseInt (in.readLine ());

			if (N == 0)
				break;

			Map<String, Integer> map = new HashMap<String, Integer> (818879);
			State curr = new State ();

			t = in.readLine ().split (" ");
			int[] coins = new int[N];

			for (int n = 0; n < N; ++n) {
				coins[n] = Integer.parseInt (t[n]);
				curr.row[n].push (coins[n]);
			}

			Arrays.sort (coins);
			State end = new State ();

			for (int n = 0; n < N; ++n)
				end.row[n].push (coins[n]);

			String end_str = end.toString (), move_str, curr_str;

			map.put (end_str, Integer.MAX_VALUE);
			map.put (curr.toString (), 0);

			Queue<State> q = new ArrayDeque<State> (35280);
			List<State> moves;
			q.offer (curr);

			while (!q.isEmpty ()) {
				curr = q.poll ();
				curr_str = curr.toString ();

				//if (!curr.equals (end)) {
				moves = moves (curr);

				for (int m = 0; m < moves.size (); ++m) {
					move_str = moves.get (m).toString ();

					if (!map.containsKey (move_str) || map.get (move_str) > map.get (curr_str) + 1) {
						map.put (move_str, map.get (curr_str) + 1);

						if (map.get (end_str) > map.get (move_str)) {
							q.offer (moves.get (m));
						}
					}
				}
				//}
			}

			System.out.println (map.get (end_str) == Integer.MAX_VALUE ? "IMPOSSIBLE" : map.get (end_str));
			//System.out.print (System.currentTimeMillis () - a);
		}
	}

	private static class State {

		Deque<Integer>[] row;

		public State () {
			this.row = new ArrayDeque[N];

			for (int r = 0; r < row.length; ++r)
				row[r] = new ArrayDeque<Integer> ();
		}

		public State (State s) {
			this.row = new ArrayDeque[s.row.length];

			for (int r = 0; r < row.length; ++r)
				row[r] = new ArrayDeque<Integer> (s.row[r]);
		}

		public boolean equals (Object o) {
			State s = (State) o;
			return this.toString ().equals (s.toString ());
		}

		public int hashCode () {
			return this.toString ().hashCode ();
		}

		public String toString () {
			StringBuilder s = new StringBuilder (22);

			for (int r = 0; r < row.length; ++r) {
				for (int i : row[r]) {
					s.append (i);
					s.append (',');
				}

				s.append (' ');
			}

			return s.toString ();
		}
	}
}