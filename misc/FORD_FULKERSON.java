import java.io.*;
import java.util.*;

/*
6
0 16 13 0 0 0
0 0 10 12 0 0
0 4 0 0 14 0
0 0 9 0 0 20
0 0 0 7 0 4
0 0 0 0 0 0
0
5

output: 23
*/

public class FORD_FULKERSON {
	
	public static boolean bfs (int [][] adj, int [] path, int s, int t) {
		Queue <Integer> queue = new ArrayDeque <Integer> ();
		boolean [] vis = new boolean [adj.length];
		queue.offer (s);
		int curr = s;
		path [s] = -1;
		
		while (!queue.isEmpty ()) {
			curr = queue.poll (); vis [curr] = true;
			
			for (int v = 0; v < adj.length; v++) {
				if (adj [curr][v] > 0 && !vis [v]) {
					vis [v] = true;
					queue.offer (v);
					path [v] = curr;
				}
			}
		}
		
		return vis [t];
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		int [][] adj = new int [N][N];
		int [] path = new int [N];
		
		for (int n = 0; n < N; ++n) {//input as adj matrix
			t = in.readLine ().split (" ");
			
			for (int n2 = 0; n2 < N; ++n2) {
				adj [n][n2] = Integer.parseInt (t [n2]);
			}
		}
		
		int source = Integer.parseInt (in.readLine ()), sink = Integer.parseInt (in.readLine ());
		int f_total = 0;
		
		while (bfs (adj, path, source, sink)) {// while path exists between source and sink
			int min_flow = Integer.MAX_VALUE;
			
			for (int curr = sink; curr != source; curr = path [curr]) {//of edges used to go from source to sink, find min capacity (edge weight)
				min_flow = Math.min (min_flow, adj [path [curr]][curr]);
			}
			
			for (int curr = sink; curr != source; curr = path [curr]) {//cancel out the min edge weight
				adj [path [curr]][curr] -= min_flow;
				adj [curr][path [curr]] += min_flow;
			}

			f_total += min_flow;
		}
		
		for (int n = 0; n < N; ++n)
			out.println (Arrays.toString (adj [n]));
		
		out.println (f_total);
		out.close ();
	}
}