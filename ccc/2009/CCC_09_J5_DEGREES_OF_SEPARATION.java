import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CCC_09_J5_DEGREES_OF_SEPARATION {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String ln;

		int x, y;
		Graph g = new Graph ();

		g.addEdge (2, 6);
		g.addEdge (1, 6);
		g.addEdge (6, 7);
		g.addEdge (6, 3);
		g.addEdge (4, 6);
		g.addEdge (6, 5);
		g.addEdge (3, 4);
		g.addEdge (4, 5);
		g.addEdge (3, 5);
		g.addEdge (3, 15);
		g.addEdge (15, 13);
		g.addEdge (13, 14);
		g.addEdge (13, 12);
		g.addEdge (12, 11);
		g.addEdge (9, 12);
		g.addEdge (11, 10);
		g.addEdge (9, 10);
		g.addEdge (9, 8);
		g.addEdge (7, 8);

		g.addEdge (16, 18);
		g.addEdge (17, 18);
		g.addEdge (16, 17);

		while (!(ln = in.readLine ()).equals ("q")) {

			if (ln.equals ("i")) {
				x = Integer.parseInt (in.readLine ());
				y = Integer.parseInt (in.readLine ());

				g.addEdge (x, y);
			}
			else if (ln.equals ("d")) {
				x = Integer.parseInt (in.readLine ());
				y = Integer.parseInt (in.readLine ());

				g.deleteEdge (x, y);
			}
			else if (ln.equals ("n")) {
				x = Integer.parseInt (in.readLine ());

				System.out.println (g.friends (x));
			}
			else if (ln.equals ("f")) {
				x = Integer.parseInt (in.readLine ());

				System.out.println (g.friendsOfFriends (x));
			}
			else if (ln.equals ("s")) {
				x = Integer.parseInt (in.readLine ());
				y = Integer.parseInt (in.readLine ());

				int result = g.degrees (x, y);

				System.out.println (result >= 0 ? result : "Not connected");
			}
		}
	}

	private static class Graph {

		Map <Integer, Set <Integer>> map = new HashMap <Integer, Set <Integer>> ();

		public void addEdge (int a, int b) {
			if (!map.containsKey (a)) {
				map.put (a, new HashSet <Integer> ());
			}

			if (!map.containsKey (b)) {
				map.put (b, new HashSet <Integer> ());
			}

			map.get (a).add (b);
			map.get (b).add (a);

			if (a == b)
				map.get (a).remove (b);
		}

		public void deleteEdge (int a, int b) {
			map.get (a).remove (b);
			map.get (b).remove (a);
		}

		public int friends (int a) {
			return map.get (a).size ();
		}

		public int friendsOfFriends (int a) {
			Set <Integer> set = new HashSet <Integer> ();

			for (Integer i : map.get (a)) {
				for (Integer x : map.get (i)) {
					if (!map.get (a).contains (x) && x != a) {
						set.add (x);
					}
				}
			}

			return set.size ();
		}

		public int degrees (int a, int b) {
			Set <Integer> set = new HashSet <Integer> ();
			Queue <Integer> queue = new ArrayDeque <Integer> ();
			int[] steps = new int[50];
			int curr;

			queue.offer (a);

			while (!queue.isEmpty ()) {
				curr = queue.poll ();

				for (Integer i : map.get (curr)) {
					if (i == b) {
						steps[b] = steps[curr] + 1;
						return steps[b];
					}
					else if (!set.contains (i)) {
						steps[i] = steps[curr] + 1;
						queue.offer (i);
						set.add (i);
					}
				}

				set.add (curr);
			}

			return -1;
		}
	}
}