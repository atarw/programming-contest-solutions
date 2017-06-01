import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GFSSOC_17_S1_PATH_TO_WATERLOO {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), T = Integer.parseInt (t[1]);

		Map<String, Integer> map = new HashMap<String, Integer> ();
		map.put ("home", 0);
		map.put ("Waterloo GO", 1);

		for (int n = 0; n < N; ++n)
			map.put (in.readLine (), map.size ());

		List<Integer>[] list = new ArrayList[map.size ()];

		for (int n = 0; n < list.length; ++n)
			list[n] = new ArrayList<Integer> ();

		for (int tt = 0, a, b; tt < T; ++tt) {
			t = in.readLine ().split ("-");
			a = map.get (t[0]);
			b = map.get (t[1]);
			list[a].add (b);
			list[b].add (a);
		}

		Queue<Integer> q = new ArrayDeque<Integer> ();
		int curr = map.get ("home");
		q.offer (curr);

		int[] dist = new int[map.size ()];
		Arrays.fill (dist, 1 << 20);
		dist[curr] = 0;

		while (!q.isEmpty ()) {
			curr = q.poll ();

			for (int v : list[curr]) {
				if (dist[v] > dist[curr] + 1) {
					dist[v] = dist[curr] + 1;
					q.offer (v);
				}
			}
		}

		out.println (dist[map.get ("Waterloo GO")] == 1 << 20 ? "RIP ACE" : dist[map.get ("Waterloo GO")]);
		out.close ();
	}
}