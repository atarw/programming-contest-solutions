import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//don't need to find scc, idk why i did.

public class DMPG_15_S6_APPLES_TO_ORANGES {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), curr;

		Map <String, Integer> map = new HashMap <String, Integer> ();
		Queue <Integer> queue = new ArrayDeque <Integer> ();
		List <Edge>[] list = new ArrayList[N];
		double[] dist = new double[N];

		for (int n = 0; n < N; n++) {
			map.put (in.readLine (), n);
		}

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");

			if (list[map.get (t[0])] == null) {
				list[map.get (t[0])] = new ArrayList <Edge> ();
			}

			list[map.get (t[0])].add (new Edge (map.get (t[1]), Double.parseDouble (t[2])));
		}

		dist[map.get ("APPLES")] = 1;
		queue.offer (map.get ("APPLES"));

		while (!queue.isEmpty ()) {
			curr = queue.poll ();

			if (curr == map.get ("APPLES") && dist[curr] > 1.1) {//floating point error???
				System.out.println ("YA");
				return;
			}

			if (list[curr] != null) {
				for (Edge e : list[curr]) {
					if (dist[curr] * e.W > dist[e.E]) {
						dist[e.E] = dist[curr] * e.W;
						queue.offer (e.E);
					}
				}
			}
		}
		System.out.println ("NAW");
	}
}

class Edge {

	int E;
	double W;

	public Edge (int E, double W) {
		this.E = E;
		this.W = W;
	}
}