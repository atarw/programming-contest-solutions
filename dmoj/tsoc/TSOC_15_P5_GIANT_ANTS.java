import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class TSOC_15_P5_GIANT_ANTS {

	static List<Integer>[] list;
	static int[] disc;
	static boolean[] dispenser;
	static boolean[] vis;

	public static int dfs (int curr, int time) {
		vis[curr] = true;

		if (time > disc[curr]) {
			vis[curr] = false;
			return 1000000;
		}
		else if (curr == list.length - 1) {
			vis[curr] = false;
			return time;
		}
		else {
			int min = 1000000;

			if (list[curr] != null) {
				for (int i : list[curr]) {
					if (!vis[i]) {
						min = Math.min (min, dfs (i, time + 1));
					}
				}
			}

			vis[curr] = false;
			return min;
		}
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), S, E;

		list = new ArrayList[N];
		dispenser = new boolean[N];
		disc = new int[N];
		vis = new boolean[N];

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			S = Integer.parseInt (t[0]) - 1;
			E = Integer.parseInt (t[1]) - 1;

			if (list[S] == null) {
				list[S] = new ArrayList<Integer> ();
			}

			if (list[E] == null) {
				list[E] = new ArrayList<Integer> ();
			}

			list[S].add (E);
			list[E].add (S);
		}

		int W = Integer.parseInt (in.readLine ());
		Deque<Integer> queue = new ArrayDeque<Integer> (W);//pre-process discovery time of nodes

		for (int w = 0; w < W; w++) {
			queue.offerFirst (Integer.parseInt (in.readLine ()) - 1);
			dispenser[queue.peek ()] = true;
		}

		int curr;

		while (!queue.isEmpty ()) {
			curr = queue.poll ();

			if (list[curr] != null) {
				for (int e = 0; e < list[curr].size (); e++) {
					if (!dispenser[list[curr].get (e)] && (disc[list[curr].get (e)] == 0 || disc[list[curr].get (e)] >
							disc[curr] + 4)) {
						disc[list[curr].get (e)] = disc[curr] + 4;
						queue.offer (list[curr].get (e));
					}
				}
			}
		}

		int min = dfs (0, 0);
		System.out.print (min == 1000000 ? "sacrifice bobhob314" : min);
	}
}