import java.io.*;
import java.util.*;

public class ICPC_16_C_BIG_TRUCK {
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int [] arr = new int [N];
		
		for (int n = 0; n < N; ++n)
			arr [n] = in.iscan ();
		
		int M = in.iscan ();
		List <Edge> [] list = new ArrayList [N];
		
		for (int n = 0; n < N; ++n)
			list [n] = new ArrayList <Edge> ();
		
		for (int m = 0, a, b, d; m < M; ++m) {
			a = in.iscan () - 1; b = in.iscan () - 1; d = in.iscan ();
			list [a].add (new Edge (b, d));
			list [b].add (new Edge (a, d));
		}
		
		State [] dp = new State [N];
		
		for (int n = 0; n < N; ++n)
			dp [n] = new State (1 << 20, arr [n]);
		
		dp [0].d = 0;
		
		Queue <Integer> q = new ArrayDeque <Integer> ();
		int curr = 0;
		q.offer (curr);
		
		while (!q.isEmpty ()) {
			curr = q.poll ();
			
			for (Edge e : list [curr]) {
				if (dp [e.E].d > dp [curr].d + e.W || dp [e.E].d == dp [curr].d + e.W && dp [e.E].i < dp [curr].i + arr [e.E]) {
					dp [e.E] = new State (dp [curr].d + e.W, dp [curr].i + arr [e.E]);
					q.offer (e.E);
				}
			}
		}

		out.print (dp [N - 1].d == 1 << 20 ? "impossible" : dp [N - 1].d + " " + dp [N - 1].i);
		out.close ();
	}
	
	private static class State {
		int d, i;
		
		public State (int d, int i) {
			this.d = d; this.i = i;
		}
	}
	
	private static class Edge {
		int E, W;
		
		public Edge (int E, int W) {
			this.E = E; this.W = W;
		}
	}

	private static class INPUT {
		
		private InputStream stream;
		private byte [] buf = new byte [1024];
		private int curChar, numChars;
		
		public INPUT (InputStream stream) {
			this.stream = stream;
		}
		
		public int cscan () throws IOException {
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
			}
			
			return buf [curChar++];
		}
		
		public int iscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();
			
			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}
			
			int res = 0;
			
			do
			{
				res = (res << 1) + (res << 3);
				res += c - '0';
				
				c = cscan ();
			}
			while (!space (c));
			
			return res * sgn;
		}
		
		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}