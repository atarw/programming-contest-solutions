import java.io.*;
import java.util.*;

public class CCC_05_S4_PYRAMID_SCHEME {
	
	static String [] order;
	static int N;

	static Map <String, Integer> map;
	
	public static int dfs (int u) {
		List <Integer> last_occ = new ArrayList <Integer> ();
		List <Integer> children = new ArrayList <Integer> ();
		Set <String> done = new HashSet <String> ();
		
		for (int n = u + 1; n < order.length; ++n)
			if (order [n].equals (order [u]))
				last_occ.add (n - 1);
		
		for (int child : last_occ)
			for (int n = u + 1; n < order.length; ++n)
				if (order [child].equals (order [n]) && done.add (order [child]))
					children.add (n);
		
		int max = 0;
		
		for (int child : children)
			max = Math.max (max, 1 + dfs (child));
		
		return max;
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int L = in.iscan ();
		
		for (int l = 0; l < L; ++l) {
			N = in.iscan ();
			order = new String [N + 1];
			map = new HashMap <String, Integer> ();
			
			for (int n = 1; n <= N; ++n)
				order [n] = in.sscan ();
			
			order [0] = order [N];
			
			for (int n = 0; n <= N; ++n)
				if (!map.containsKey (order [n]))
					map.put (order [n], map.size ());
			
			int level = dfs (0);
			out.println (N * 10 - 20 * level);
		}

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