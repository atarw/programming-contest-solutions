import java.io.*;
import java.util.*;

public class CCO_02_P2_CONNECT_THE_CAMPUS {
	
	static int N;
	static int [] x, y, parent;
	static int [][] dist;
	
	public static int find (int u) {
		if (parent [u] != u)
			return parent [u] = find (parent [u]);
		
		return parent [u];
	}
	
	public static boolean union (int u, int v) {
		int r1 = find (u), r2 = find (v);
		
		if (r1 == r2)
			return false;
		
		parent [r1] = r2;
		find (u); find (v);
		return true;
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		x = new int [N]; y = new int [N];
		parent = new int [N];
		dist = new int [N][N];
		
		for (int n = 0; n < N; ++n) {
			x [n] = in.iscan (); y [n] = in.iscan ();
			parent [n] = n;
		}
		
		for (int n = 0; n < N; ++n)
			for (int n2 = n + 1; n2 < N; ++n2)
				dist [n][n2] = dist [n2][n] = (y [n] - y [n2]) * (y [n] - y [n2]) + (x [n] - x [n2]) * (x [n] - x [n2]);
		
		List <Edge> list = new ArrayList <Edge> ();
		
		for (int n = 0; n < N; ++n)
			for (int n2 = n + 1; n2 < N; ++n2)
				list.add (new Edge (n, n2));
		
		int M = in.iscan ();
		int comp = N;
		
		for (int m = 0, a, b; m < M; ++m) {
			a = in.iscan () - 1; b = in.iscan () - 1;
			
			if (union (a, b))
				--comp;
		}
			
		double length = 0;
		List <Edge> used = new ArrayList <Edge> ();
		
		Collections.sort (list);
		
		for (Edge e : list) {
			if (comp <= 1)
				break;
			
			if (union (e.u, e.v)) {
				used.add (e);
				length += Math.sqrt (dist [e.u][e.v]);
				--comp;
			}
		}
		
		out.printf ("%.2f\n", length);
		
		for (Edge e : list)
			out.println ((e.u + 1) + " " + (e.v + 1));
		
		out.close ();
	}
	
	private static class Edge implements Comparable <Edge> {
		int u, v;
		
		public int compareTo (Edge e) {
			return Integer.compare (dist [this.u][this.v], dist [e.u][e.v]);
		}
		
		public boolean equals (Object o) {
			Edge e = (Edge) o;
			return u == e.u && v == e.v;
		}
		
		public String toString () {
			return (u + 1) + " " + (v + 1) + " -> " + dist [u][v] + "\n";
		}
		
		public int hashCode () {
			return u * 17 + v * 31;
		}
		
		public Edge (int u, int v) {
			this.u = u; this.v = v;
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
			
			do {
				res = (res << 1) + (res << 3);
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
				if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());
				res *= 10;
				res += c - '0';
				c = cscan ();
			}
			
			if (c == '.') {
				c = cscan ();
				double m = 1;
				
				while (!space (c)) {
					if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ());
					
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
	
	public static class UTILITIES {
		
		static final double EPS = 10e-6;
		
		public static int lower_bound (int [] arr, int x) {
			int low = 0, high = arr.length, mid = -1;
			
			while (low < high) {
				mid = (low + high) / 2;
				
				if (arr [mid] >= x)
					high = mid;
				else
					low = mid + 1;
			}
			
			return low;
		}
		
		public static int upper_bound (int [] arr, int x) {
			int low = 0, high = arr.length, mid = -1;
			
			while (low < high) {
				mid = (low + high) / 2;
				
				if (arr [mid] > x)
					high = mid;
				else
					low = mid + 1;
			}
			
			return low;
		}
		
		public static int gcd (int a, int b) {
			return b == 0 ? a : gcd (b, a % b);
		}
		
		public static int lcm (int a, int b) {
			return a * b / gcd (a, b);
		}

		public static int fast_pow_mod (int b, int x, int mod) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow_mod (b * b % mod, x / 2, mod) % mod;
			
			return b * fast_pow_mod (b * b % mod, x / 2, mod) % mod;
		}

		public static int fast_pow (int b, int x) {
			if (x == 0) return 1;
			if (x == 1) return b;
			if (x % 2 == 0) return fast_pow (b * b, x / 2);
			
			return b * fast_pow (b * b, x / 2);
		}
		
		public static long choose (long n, long k) {
			k = Math.min (k, n - k);
			long val = 1;
			
			for (int i = 0; i < k; ++i)
				val = val * (n - i) / (i + 1);
				
			return val;
		}
		
		public static long permute (int n, int k) {
			if (n < k) return 0;
			long val = 1;
			
			for (int i = 0; i < k; ++i)
				val = (val * (n - i));
				
			return val;
		}
	}
}