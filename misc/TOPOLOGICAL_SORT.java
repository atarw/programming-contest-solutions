import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TOPOLOGICAL_SORT {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]); // N is vertices, M is edges
		List<Integer>[] list = new ArrayList[N];
		int[] indegree = new int[N]; // indegree [x] represents number of nodes with an edge pointing towards node x

		for (int n = 0; n < N; ++n)
			list[n] = new ArrayList<Integer> ();

		for (int m = 0, a, b; m < M; ++m) {
			t = in.readLine ().split (" ");
			a = Integer.parseInt (t[0]);
			b = Integer.parseInt (t[1]);
			list[a].add (b); // directed edge from a to b
			++indegree[b];
		}

		Queue<Integer> q = new ArrayDeque<Integer> ();
		List<Integer> order = new ArrayList<Integer> ();

		for (int n = 0; n < N; ++n)
			if (indegree[n] == 0) // if any nodes don't have edges pointing towards them, they can be first
				q.offer (n);

		int u;

		while (!q.isEmpty ()) {
			u = q.poll ();
			order.add (u);

			for (int v : list[u])
				if (--indegree[v] == 0) // if after removing node u from the graph, if nodes connected to node u don't
					// have edges pointing towards them, add them to the queue
					q.offer (v);
		}

		if (order.size () != N) // if not all nodes are included in the sorted list, there is a cycle in the graph
			out.print (-1);
		else
			out.print (order);

		out.close ();
	}
}