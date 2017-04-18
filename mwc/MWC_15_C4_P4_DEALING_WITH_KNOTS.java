import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MWC_15_C4_P4_DEALING_WITH_KNOTS {

	static List <Integer>[] list;
	static boolean[] vis;

	public static boolean find (int curr, int end) {
		if (curr == end) {
			return true;
		}

		vis[curr] = true;

		if (list[curr] != null) {
			for (int i = 0; i < list[curr].size (); i++) {
				if (!vis[list[curr].get (i)] && find (list[curr].get (i), end)) {
					return true;
				}
			}
		}

		vis[curr] = false;
		return false;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ()), S, E;
		list = new ArrayList[N];

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			S = Integer.parseInt (t[0]) - 1;
			E = Integer.parseInt (t[1]) - 1;

			if (list[S] == null) {
				list[S] = new ArrayList <Integer> ();
			}

			list[S].add (E);
		}

		vis = new boolean[N];
		t = in.readLine ().split (" ");

		System.out.println (find (Integer.parseInt (t[0]) - 1, Integer.parseInt (t[1]) - 1) ? "Tangled" : "Not " +
				"Tangled");
	}
}