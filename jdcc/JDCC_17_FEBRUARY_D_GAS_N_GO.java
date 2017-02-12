import java.io.*;
import java.util.*;

public class JDCC_17_FEBRUARY_D_GAS_N_GO {
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT ("input_2.txt");
		PrintWriter out = new PrintWriter (System.out);
		
		int N = in.iscan (), M = in.iscan ();
		int [] price = new int [N];
		List <Edge> [] list = new ArrayList [N];
		
		for (int n = 0; n < N; ++n)
			price [n] = in.iscan ();
		
		//System.out.println ("done reading prices");
		
		for (int n = 0; n < N; ++n)
			list [n] = new ArrayList <Edge> ();
		
		for (int m = 0, a, b, c; m < M; ++m) {
			//System.out.println (m + " " + M + " " + (m < M));
			a = in.iscan () - 1; b = in.iscan () - 1; c = in.iscan ();
			list [a].add (new Edge (b, c)); list [b].add (new Edge (a, c));
		}
		
		//System.out.println (3);
		
		State [] dp = new State [N];
		
		dp [0] = new State (0, price [0]);
		
		for (int n = 1; n < N; ++n)
			dp [n] = new State (1 << 20, price [n]);
		
		Queue <Integer> q = new ArrayDeque <Integer> ();
		q.offer (0);
		
		int curr;
		
		while (!q.isEmpty ()) {
			curr = q.poll ();
			
			for (Edge e : list [curr]) {
				if (dp [e.v].money > dp [curr].money + e.w * dp [curr].min) {
					dp [e.v] = new State (dp [curr].money + e.w * dp [curr].min, Math.min (dp [e.v].min, dp [curr].min));
					q.offer (e.v);
				}
			}
		}
		
		out.print (dp [N - 1].money);
		out.close ();
	}
	
	private static class State {
		int money, min;
		
		public State (int money, int min) {
			this.money = money; this.min = min;
		}
	}
	
	private static class Edge {
		int v, w;
		
		public Edge (int v, int w) {
			this.v = v; this.w = w;
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