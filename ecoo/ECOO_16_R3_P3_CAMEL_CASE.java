import java.io.*;
import java.util.*;

public class ECOO_16_R3_P3_CAMEL_CASE {
	
	static Node root = new Node (false);
	static String str;
	static int [] dp;
	
	public static int conv (int c) {
		if (c == '\'')
			return 26;
		return c - 'a';
	}
	
	public static int solve (int n) {
		//System.out.println (str + " " + str.substring (n));
		
		if (dp [n] != -1)
			return dp [n];
		
		if (n == str.length ())
			return dp [n] = 0;
		
		dp [n] = 1 << 20;
		Node cur = root;
		
		for (int nn = n; nn < str.length (); ++nn) {
			if (cur.children [conv (str.charAt (nn))] != null) {
				if (cur.children [conv (str.charAt (nn))].end)
					dp [n] = Math.min (dp [n], 1 + solve (nn + 1));
				
				cur = cur.children [conv (str.charAt (nn))];
			}
			else
				break;
		}
		
		return dp [n];
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		Node cur = null;
		String s;
		
		for (int n = 0, c; n < N; ++n) {
			cur = root;
			s = in.sscan ();
			
			for (int ss = 0; ss < s.length (); ++ss) {
				if (cur.children [conv (s.charAt (ss))] == null)
					cur.children [conv (s.charAt (ss))] = new Node (ss == s.length () - 1);
				else
					cur.children [conv (s.charAt (ss))].end |= (ss == s.length () - 1);
				
				cur = cur.children [conv (s.charAt (ss))];
			}
		}
		
		for (int tt = 0; tt < 10; ++tt) {
			str = in.sscan ();
			dp = new int [str.length () + 1];
			Arrays.fill (dp, -1);
			out.println (solve (0) - 1);
		}

		out.close ();
	}
	
	private static class Node {
		boolean end = false;
		Node [] children = new Node [27];
		
		public Node (boolean end) {
			this.end = end;
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