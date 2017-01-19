import java.io.*;
import java.util.*;

public class haybales2 {
	
	static int N, Q;
	static long [] arr;
	static long [] sum, min, lazy;
	
	public static int l (int u) {
		return u * 2 + 1;
	}
	
	public static int r (int u) {
		return u * 2 + 2;
	}
	
	public static long min (int u) {
		return min [u] + lazy [u];
	}
	
	public static long sum (int u, int l, int r) {
		return sum [u] + lazy [u] * (r - l + 1);
	}
	
	public static void pull_up (int u, int l, int r) {
		int mid = (l + r) / 2;
		
		sum [u] = sum (l (u), l, mid) + sum (r (u), mid + 1, r);
		min [u] = Math.min (min (l (u)), min (r (u)));
	}
	
	public static void push_down (int u, int l, int r) {
		sum [u] += lazy [u] * (r - l + 1);
		min [u] += lazy [u];
		
		if (l != r) {
			lazy [l (u)] += lazy [u];
			lazy [r (u)] += lazy [u];
		}
		
		lazy [u] = 0;
	}
	
	public static void build (int u, int l, int r) {
		if (l == r) {
			sum [u] = min [u] = arr [l];
			return;
		}
		
		int mid = (l + r) / 2;
		build (l (u), l, mid);
		build (r (u), mid + 1, r);
		
		sum [u] = sum (l (u), l, r) + sum (r (u), l, r);
		min [u] = Math.min (min (l (u)), min (r (u)));
	}
	
	public static void update (int u, int l, int r, int ql, int qr, int v) {
		if (r < ql || l > qr)
			return;
		
		if (ql <= l && r <= qr) {
			lazy [u] += v;
			return;
		}
		
		push_down (u, l, r);
		
		int mid = (l + r) / 2;
		update (l (u), l, mid, ql, qr, v);
		update (r (u), mid + 1, r, ql, qr, v);
		
		pull_up (u, l, r);
	}
	
	public static long query_sum (int u, int l, int r, int ql, int qr) {
		if (r < ql || l > qr)
			return 0;
		
		if (ql <= l && r <= qr)
			return sum (u, l, r);
		
		push_down (u, l, r);
		
		int mid = (l + r) / 2;
		long val = query_sum (l (u), l, mid, ql, qr) + query_sum (r (u), mid + 1, r, ql, qr);
		pull_up (u, l, r);
		
		return val;
	}
	
	public static long query_min (int u, int l, int r, int ql, int qr) {
		if (r < ql || l > qr)
			return 1L << 50;
		
		if (ql <= l && r <= qr)
			return min (u);
		
		push_down (u, l, r);
		
		int mid = (l + r) / 2;
		long val = 1L << 50;
		
		val = Math.min (val, query_min (l (u), l, mid, ql, qr));
		val = Math.min (val, query_min (r (u), mid + 1, r, ql, qr));
		pull_up (u, l, r);
		
		return val;
	}
		
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);
		
		N = in.iscan (); Q = in.iscan ();
		arr = new long [N];
		sum = new long [4 * N];
		min = new long [4 * N];
		lazy = new long [4 * N];
		
		for (int n = 0; n < N; ++n)
			arr [n] = in.iscan ();
		
		build (0, 0, N - 1);
		
		for (int q = 0, c, a, b; q < Q; ++q) {
			c = in.cscan ();
			a = in.iscan () - 1; b = in.iscan () - 1;
			
			if (c == 'M') // query min
				out.println (query_min (0, 0, N - 1, a, b));
			else if (c == 'P') // increment range
				update (0, 0, N - 1, a, b, in.iscan ());
			else // query sum
				out.println (query_sum (0, 0, N - 1, a, b));
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
		
		// read one character
		public int cscan () throws IOException {
			//if (numChars == -1) throw new InputMismatchException ();
			
			if (curChar >= numChars) {
				curChar = 0;
				numChars = stream.read (buf);
				
				//if (numChars <= 0) return -1;
			}
			
			return buf [curChar++];
		}
		
		// read an int
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
		
		// read a string
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
		
		// read a double
		public double dscan () throws IOException {
			int c = cscan (), sgn = 1;
			while (space (c)) c = cscan ();
			
			if (c == '-') {
				sgn = -1;
				c = cscan ();
			}
			
			double res = 0;
			
			while (!space (c) && c != '.') {
				if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
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
					if (c == 'e' || c == 'E') return res * UTILITIES.fast_pow (10, iscan ()); /*Math.pow (10, iscan ());*/
					//if (c < '0' || c > '9') throw new InputMismatchException ();
					
					m /= 10;
					res += (c - '0') * m;
					c = cscan ();
				}
			}
			
			return res * sgn;
		}
		
		// read a long
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
