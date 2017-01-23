import java.io.*;
import java.util.*;

public class CCO_13_P3_LHC {
	
	static List <Integer> [] list;
	static boolean [] vis;
	static int [] dist;
	static long [] cnt;
	
	static int furthest_node, furthest_dist, half_diam;
	
	public static void pre (int u) {
		furthest_dist = dist [u] = 0;
		furthest_node = u;
	}
	
	public static void dfs (int u) {
		vis [u] = true;
		
		if (dist [u] > furthest_dist) {
			furthest_dist = dist [u];
			furthest_node = u;
		}
		
		for (int v : list [u]) {
			if (!vis [v]) {
				dist [v] = dist [u] + 1;
				dfs (v);
			}
		}
		
		vis [u] = false;
	}
	
	public static long dfs2 (int u, int d) {
		if (d == half_diam)
			return ++cnt [u];
		
		vis [u] = true;
		
		for (int v : list [u])
			if (!vis [v])
				cnt [u] += dfs2 (v, d + 1);
		
		vis [u] = false;
		return cnt [u];
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		list = new ArrayList [N];
		vis = new boolean [N];
		dist = new int [N];
		
		for (int n = 0; n < N; ++n)
			list [n] = new ArrayList <Integer> ();
		
		for (int n = 0, i, j; n < N - 1; ++n) {
			i = in.iscan () - 1; j = in.iscan () - 1;
			list [i].add (j); list [j].add (i);
		}
		
		// find centers
		pre (0); dfs (0);
		int a = furthest_node;
		pre (a); dfs (a);
		int b = furthest_node, diam = furthest_dist;
		long ways = 0L;
		
		if (diam % 2 == 0) { // one center
			int u = b;
			
			while (u != a && dist [u] * 2 != diam) {
				for (int v : list [u]) {
					if (dist [v] < dist [u]) {
						u = v;
						break;
					}
				}
			}
			
			cnt = new long [N];
			half_diam = dist [u];
			dfs2 (u, 0);
			
			// cnt [u] = sum of all cnt
			for (int v : list [u])
				ways += cnt [v] * (cnt [u] - cnt [v]);
			
			ways /= 2L;
		}
		else { // two centers
			int u = b, u2 = b;
			
			while (u != a && dist [u] * 2 + 1 != diam) {
				for (int v : list [u]) {
					if (dist [v] < dist [u]) {
						u2 = u; u = v;
						break;
					}
				}
			}
			
			cnt = new long [N];
			half_diam = dist [u];
			
			vis [u2] = true; dfs2 (u, 0);
			vis [u] = true; dfs2 (u2, 0);
			
			ways = cnt [u] * cnt [u2];
		}
		
		out.print ((diam + 1) + " " + ways);
		out.close ();
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