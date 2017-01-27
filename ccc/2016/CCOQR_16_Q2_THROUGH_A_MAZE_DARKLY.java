import java.io.*;
import java.util.*;

public class CCOQR_16_Q2_THROUGH_A_MAZE_DARKLY {
		
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan (), M = 0, ID = 0;
		List <Integer> [] list = new ArrayList [N];
		Edge [] edges = new Edge [500001];
		Map <Integer, Pair> [] map = new HashMap [N];
		
		for (int n = 0, K; n < N; ++n) {
			K = in.iscan (); M += K;
			list [n] = new ArrayList <Integer> (K + 1);
			map [n] = new HashMap <Integer, Pair> (K, 1.1f);
			
			for (int k = 0, e; k < K; ++k) {
				e = in.iscan () - 1;
				edges [ID] = new Edge (n, e, ID);
				map [n].put (e, new Pair (k, K));
				list [n].add (ID); ++ID;
			}
		}
		
		int [] next_edge = new int [M];
		
		for (int m = 0, s, e; m < M; ++m) {
			s = edges [m].s; e = edges [m].e;
			next_edge [m] = list [e].get (map [e].get (s).k == 0 ? map [e].get (s).K - 1 : map [e].get (s).k - 1);
		}
		
		int [] max_cycle = new int [N], first = new int [N];
		boolean [] vis_edge = new boolean [M];

		for (int n = 0; n < N; ++n) {
			for (int i = 0, curr_edge, time, vis, s, e; i < list [n].size (); ++i) {
				curr_edge = list [n].get (i);
				time = 1; vis = 0;
				
				if (vis_edge [curr_edge])
					continue;
				
				while (vis <= 2) {
					s = edges [curr_edge].s; e = edges [curr_edge].e;
					vis_edge [curr_edge] = true;
					
					if (curr_edge == list [n].get (i))
						++vis;
					
					if (first [s] != 0)
						max_cycle [s] = Math.max (max_cycle [s], time - first [s]);
					
					first [s] = time; ++time;
					curr_edge = next_edge [curr_edge];
				}
			}
		}
		
		int Q = in.iscan ();
		
		for (int q = 0; q < Q; ++q)
			out.println (max_cycle [in.iscan () - 1]);
		
		out.close ();
	}
	
	private static class Pair {
		int k, K;
		
		public Pair (int k, int K) {
			this.k = k; this.K = K;
		}
	}
	
	private static class Edge {
		int s, e, id;
		
		public Edge (int s, int e, int id) {
			this.s = s; this.e = e; this.id = id;
		}
	}

	private static class INPUT {
		
		private InputStream stream;
		private byte [] buf = new byte [1024];
		private int curChar, numChars;
		
		public INPUT (InputStream stream) {
			this.stream = stream;
		}
		
		public INPUT (String file) throws IOException {
			this.stream = new FileInputStream (file);
		}
		
		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);
			
			return b * fast_pow (b * b, x / 2);
		}
		
		public int cscan () throws IOException {
			//if (numChars == -1) throw new InputMismatchException ();
			
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
				
				//if (numChars <= 0) return -1;
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
				//if (c < '0' || c > '9') throw new InputMismatchException ();
				
				res = (res << 1) + (res << 3);
				//res *= 10;
				res += c - '0';
				
				c = cscan ();
			}
			while (!space (c));
			
			return res * sgn;
		}
		
		public String sscan () throws IOException {
			int c = cscan ();
			while (space (c)) c = cscan();
			
			StringBuilder res = new StringBuilder();
			
			do
			{
				res.appendCodePoint (c);
				c = cscan ();
			}
			while (!space (c));
			
			return res.toString ();
		}
		
		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();
			
			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}
			
			double res = 0;
			
			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E') return res * fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
				//if (c < '0' || c > '9') throw new InputMismatchException ();
				
				//res = (res << 1) + (res << 3);
				res *= 10;
				res += c - '0';
				c = cscan ();
			}
			
			if (c == '.') {
				c = cscan ();
				double m = 1;
				
				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
					//if (c < '0' || c > '9') throw new InputMismatchException ();
					
					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}
			
			return res * sgn;
		}
		
		public long lscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();
			
			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}
			
			long res = 0;
			
			do {
				//if (c < '0' || c > '9') throw new InputMismatchException();
				
				res = (res << 1) + (res << 3);
				//res *= 10;
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