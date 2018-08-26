import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class DISJOINT_SET_TEST {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);
		DISJOINT_SET ds = new DISJOINT_SET (N);
		Edge[] arr = new Edge[M];
		int size = N - 1;

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			arr[m] = new Edge (Integer.parseInt (t[0]) - 1, Integer.parseInt (t[1]) - 1);
		}

		List<Integer> list = new ArrayList<Integer> (M);

		for (int e = 0; e < arr.length; e++) {
			if (ds.find (arr[e].S) != ds.find (arr[e].E)) {
				list.add (e + 1);
				ds.union (arr[e].S, arr[e].E);
				size--;
			}
		}

		if (size == 0) {
			for (int i = 0; i < list.size (); i++) {
				System.out.println (list.get (i));
			}
		}
		else {
			System.out.println ("Disconnected Graph");
		}
	}

	private static class Edge {

		int S, E;

		public Edge (int S, int E) {
			this.S = S;
			this.E = E;
		}
	}

	private static class DISJOINT_SET {

		//parent stores root of tree containing item x, rank stores depth of trees containing item x
		int[] parent, rank;

		public DISJOINT_SET (int N) {
			parent = new int[N];
			rank = new int[N];

			for (int n = 0; n < N; n++) {
				parent[n] = n;
				rank[n] = 0;
			}
		}

		public void union (int item1, int item2) {
			int root1 = find (item1), root2 = find (item2);

			if (root1 != root2) {
				if (rank[root1] < rank[root2]) {
					parent[root1] = root2;
				}
				else if (rank[root1] > rank[root2]) {
					parent[root2] = root1;
				}
				else {
					parent[root2] = root1;
					rank[root1]++;
				}

				find (item1);
				find (item2);
			}
		}

		public int find (int item) {
			if (parent[item] != item) {
				parent[item] = find (parent[item]);
			}

			return parent[item];
		}
	}
}