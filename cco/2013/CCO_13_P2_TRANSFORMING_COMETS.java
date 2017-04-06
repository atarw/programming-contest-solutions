import java.io.*;
import java.util.*;

public class CCO_13_P2_TRANSFORMING_COMETS {
	
	static int [] x1, x2, y1, y2;
	static double [] ang1, ang2;
	
	public static int kmp (double [] txt, double [] pattern) {
		// preprocess
		int [] arr = new int [pattern.length];
		arr [0] = 0;
		
		int i = 1;
		int len = 0;
		
		while (i < pattern.length) {
			if (equals (pattern [i], pattern [len])) {
				++len;
				arr [i] = len;
				++i;
			}
			else {
				if (len != 0) {
					len = arr [len - 1];
				}
				else {
					arr [i] = len;
					++i;
				}
			}
		}
		
		// find
		i = 0; int j = 0;
		int first = -1;
		
		while (i < txt.length) {
			if (equals (pattern [j], txt [i])) {
				++i; 
				++j;
			}
			
			if (j == pattern.length) {
				//System.out.println ("found pattern at " + (i - j));
				return i - j;
				
				//if (first == -1)
				//	first = i - j;
				
				//j = arr [j - 1];
			}
			else if (i < txt.length && !equals (pattern [j], txt [i])) {
				if (j != 0) {
					j = arr [j - 1];
				}
				else {
					++i;
				}
			}
		}
		
		return first;
	}
	
	public static double angle (int x1, int y1, int x2, int y2, int x3, int y3) {
		// (x2,y2) as centre
		int abx = x1 - x2, aby = y1 - y2;
		int acx = x3 - x2, acy = y3 - y2;
		
		int dot = abx * acx + aby * acy;
		int cross = abx * acy - aby * acx;
		
		double distab = Math.sqrt (abx * abx + aby * aby);
		double distac = Math.sqrt (acx * acx + acy * acy);
		
		// need to multiply by dist to check equality amongst right angles
		return Math.atan2 (cross, dot) * (distab / distac);
	}
	
	public static boolean equals (double a, double b) {
		return Math.abs (a - b) <= UTILITIES.EPS;
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int T = in.iscan ();
		
		for (int tt = 0, N; tt < T; ++tt) {
			N = in.iscan ();
			
			x1 = new int [N];
			x2 = new int [N];
			y1 = new int [N];
			y2 = new int [N];
			
			for (int n = 0; n < N; ++n) {
				x1 [n] = in.iscan ();
				y1 [n] = in.iscan ();
			}
			
			for (int n = 0; n < N; ++n) {
				x2 [n] = in.iscan ();
				y2 [n] = in.iscan ();
			}
			
			ang1 = new double [N];
			ang2 = new double [N * 2];
			
			for (int n = 0; n < 2 * N; ++n) {
				if (n < N) {
					ang1 [n] = angle (x1 [n], y1 [n], x1 [(n + 1) % N], y1 [(n + 1) % N], x1 [(n + 2) % N], y1 [(n + 2) % N]);
					ang2 [n] = angle (x2 [n], y2 [n], x2 [(n + 1) % N], y2 [(n + 1) % N], x2 [(n + 2) % N], y2 [(n + 2) % N]);
				}
				else {
					ang2 [n] = ang2 [n - N];
				}
			}

			out.println (kmp (ang2, ang1) + 1);
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