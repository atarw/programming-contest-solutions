import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VMSS_16_P3_COLD_WAR_TELECOM {

	static List<Integer>[] list;
	static boolean[] visited, art;
	static int[] low, disc, parent;
	static int time = 0, total = 0;

	public static void dfs (int curr) {
		visited[curr] = true;
		time++;
		low[curr] = disc[curr] = time;

		int child = 0, next;

		for (int i = 0; i < list[curr].size (); i++) {
			next = list[curr].get (i);

			if (!visited[next]) {
				child++;
				parent[next] = curr;
				dfs (next);

				low[curr] = Math.min (low[curr], low[next]);

				if (!art[curr] && (parent[curr] != -1 && low[next] >= disc[curr] || parent[curr] == -1 && child > 1)) {
					art[curr] = true;
					total++;
				}
			}
			else if (next != parent[curr]) {
				low[curr] = Math.min (low[curr], disc[next]);
			}
		}
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), S, E;
		list = new ArrayList[N];
		visited = new boolean[N];
		art = new boolean[N];
		low = new int[N];
		disc = new int[N];
		parent = new int[N];

		Arrays.fill (parent, -1);

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

		dfs (0);

		System.out.println (total);

		for (int n = 0; n < N; n++) {
			if (art[n]) {
				System.out.println (n + 1);
			}
		}
	}
}