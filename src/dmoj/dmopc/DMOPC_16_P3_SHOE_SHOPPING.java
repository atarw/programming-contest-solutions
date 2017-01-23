import java.io.*;
import java.util.*;

public class DMOPC_16_P3_SHOE_SHOPPING {
	
	static int N;
	static int [] arr;
	static double [] cache;
	
	public static double solve (int n) {
		if (cache [n] != Double.POSITIVE_INFINITY)
			return cache [n];
		
		if (n == N)
			return cache [n] = 0.0;
			
		if (n + 1 < N)
			cache [n] = Math.max (arr [n], arr [n + 1]) + 0.5 * Math.min (arr [n], arr [n + 1]) + solve (n + 2);
		
		if (n + 2 < N)
			cache [n] = Math.min (cache [n], arr [n] + arr [n + 1] + arr [n + 2] - Math.min (arr [n], Math.min (arr [n + 1], arr [n + 2])) + solve (n + 3));

		cache [n] = Math.min (cache [n], arr [n] + solve (n + 1));
		return cache [n];
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		arr = new int [N]; cache = new double [N + 1];
		
		Arrays.fill (cache, Double.POSITIVE_INFINITY);
		
		for (int n = 0; n < N; ++n)
			arr [n] = in.iscan ();
		
		out.printf ("%.1f", solve (0));
		out.close ();
	}

	private static class INPUT {
		
		private InputStream stream;
		private byte [] buf = new byte [1024];
		private int curChar, numChars;
		
		public INPUT (InputStream stream) {
			this.stream = stream;
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
		
		public boolean space (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}