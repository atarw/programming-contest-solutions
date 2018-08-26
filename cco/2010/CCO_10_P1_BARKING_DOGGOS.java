import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// atharva washimkar
// August 11, 2018

public class CCO_10_P1_BARKING_DOGGOS {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int D = Integer.parseInt (in.readLine ());
		int[] wait = new int[D];
		List<Integer>[] list = new ArrayList[D];

		for (int d = 0; d < D; ++d) {
			wait[d] = Integer.parseInt (in.readLine ());
			list[d] = new ArrayList<Integer> ();
		}

		int F = Integer.parseInt (in.readLine ());

		for (int f = 0; f < F; ++f) {
			t = in.readLine ().split (" ");
			list[Integer.parseInt (t[0]) - 1].add (Integer.parseInt (t[1]) - 1);
		}

		int T = Integer.parseInt (in.readLine ());

		int[] doggos = new int[D];
		int[] busy = new int[D];

		PriorityQueue<Bark> q = new PriorityQueue<Bark> ();
		q.offer (new Bark (0, 0));

		Bark curr;

		while (!q.isEmpty ()) {
			curr = q.poll ();

			if (busy[curr.doggo] != 0 && curr.time <= busy[curr.doggo] + wait[curr.doggo])
				continue;

			++doggos[curr.doggo];
			busy[curr.doggo] = curr.time;

			for (int doggo : list[curr.doggo])
				if (curr.time + wait[doggo] <= T)
					q.offer (new Bark (doggo, curr.time + wait[doggo]));
		}

		for (int doggo : doggos)
			out.println (doggo);

		out.close ();
	}

	private static class Bark implements Comparable<Bark> {

		int doggo, time;

		public Bark (int doggo, int time) {
			this.doggo = doggo;
			this.time = time;
		}

		public int compareTo (Bark b) {
			return this.time - b.time;
		}
	}
}