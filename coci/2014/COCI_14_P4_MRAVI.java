import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class COCI_14_P4_MRAVI {

	static int[] parent, indegree;
	static double[] needed, flowup;
	static boolean[] superup;

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		parent = new int[N];
		superup = new boolean[N];
		flowup = new double[N];
		needed = new double[N];
		indegree = new int[N];

		int S, E, X, T;//wow what a coincidence

		for (int n = 0; n < N - 1; n++) {
			t = in.readLine ().split (" ");
			S = Integer.parseInt (t[0]) - 1;
			E = Integer.parseInt (t[1]) - 1;
			X = Integer.parseInt (t[2]);
			T = Integer.parseInt (t[3]);

			parent[E] = S;
			flowup[E] = X / 100.0;
			superup[E] = T == 1;
			indegree[S]++;
		}

		Deque<Integer> topo = new ArrayDeque<Integer> (), queue = new ArrayDeque<Integer> ();

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			needed[n] = Integer.parseInt (t[n]);

			if (needed[n] != -1) {
				topo.offer (n);
			}
			else {
				needed[n] = 0;
			}
		}

		int curr = 0;

		while (!topo.isEmpty ()) {
			curr = topo.poll ();
			queue.offer (curr);

			indegree[parent[curr]]--;

			if (indegree[parent[curr]] == 0) {
				topo.offerLast (parent[curr]);
			}
		}

		while (!queue.isEmpty ()) {
			curr = queue.poll ();

			if (curr != 0) {
				if (superup[curr]) {
					needed[parent[curr]] = Math.max (needed[parent[curr]], Math.sqrt (needed[curr]) / flowup[curr]);
				}
				else {
					needed[parent[curr]] = Math.max (needed[parent[curr]], needed[curr] / flowup[curr]);
				}
			}
		}

		System.out.print (needed[0]);
	}
}