import java.io.*;
import java.util.*;

public class DMOPC_14_P4_EXAM_DELAY {
	
	public static void main(String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		
		int V = Integer.parseInt (in.readLine ()), E = Integer.parseInt (in.readLine ());
		List <Edge> [] list = new ArrayList [V];
		Edge [] edges = new Edge [E * 2];
		
		int m, n, d, s, i = 0;
		
		for (int e = 0; e < E; e++) {
			t = in.readLine ().split (" ");
			m = Integer.parseInt (t [0]) - 1; n = Integer.parseInt (t [1]) - 1; d = Integer.parseInt (t [2]); s = Integer.parseInt (t [3]);
			
			if (list [m] == null) {
				list [m] = new ArrayList <Edge> ();
			}
			
			if (list [n] == null) {
				list [n] = new ArrayList <Edge> ();
			}
			
			edges [i] = new Edge (m, n, d, s, i); list [m].add (edges [i]); i++;
			edges [i] = new Edge (n, m, d, s, i); list [n].add (edges [i]); i++;
		}
		
		State [] cache = new State [V];//curr node -> min time + min intersections
		Arrays.fill (cache, new State (Double.MAX_VALUE, Integer.MAX_VALUE));
		cache [0] = new State (0, 0);
		
		int [] parent = new int [V];//stores edge ID which was taken to get to node v 
		
		Deque <Integer> q = new ArrayDeque <Integer> ();
		int curr = 0; q.offer (curr);
		
		while (!q.isEmpty ()) {
			curr = q.poll ();
			
			if (curr != V - 1) {
				for (Edge e : list [curr]) {
					if (cache [e.N].time > cache [curr].time + (double)(e.D) / e.S || cache [e.N].time == cache [curr].time + (double)(e.D) / e.S && cache [e.N].inter > cache [curr].inter + 1) {
						cache [e.N] = new State (cache [curr].time + (double)(e.D) / e.S, cache [curr].inter + 1);
						q.offer (e.N);
						
						parent [e.N] = e.I;
					}
				}
			}
		}
		
		System.out.println (cache [V - 1].inter);
		
		List <Edge> used = new ArrayList <Edge> ();
		curr = V - 1;
		
		while (curr != 0) {
			used.add (edges [parent [curr]]);
			curr = edges [parent [curr]].M;
		}
		
		double totalTime = 0;
		
		for (Edge e : used) {
			totalTime += 60 * (e.D / (double)(e.S * 0.75));
		}
		
		System.out.println (Math.round (totalTime - cache [V - 1].time * 60));
	}
}

class State {
	double time;
	int inter;
	
	public State (double time, int inter) {
		this.time = time; this.inter = inter;
	}
}

class Edge {
	int M, N, D, S, I;
	
	public Edge (int M, int N, int D, int S, int I) {
		this.M = M; this.N = N; this.D = D; this.S = S; this.I = I;
	}
}