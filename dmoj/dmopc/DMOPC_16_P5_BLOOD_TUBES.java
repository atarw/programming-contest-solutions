import java.io.*;
import java.util.*;

public class DMOPC_16_P5_BLOOD_TUBES {
	
	static int [] bit;
	
	public static void update (int pos, int v) {
		while (pos < bit.length) {
			bit [pos] += v;
			pos += pos & -pos;
		}
	}
	
	public static int query (int pos) {
		int ret = 0;
		
		while (pos > 0) {
			ret += bit [pos];
			pos -= pos & -pos;
		}
		
		return ret;
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int [] arr = new int [N];
		bit = new int [N + 1];

		for (int n = 0; n < N; ++n)
			arr [n] = in.iscan ();

		long inv = 0L;
		
		for (int n = 0, v; n < N; ++n) {
			v = query (arr [n]);
			inv += Math.min (v, n - v);
			update (arr [n], 1);
		}
		
		out.print (inv);
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