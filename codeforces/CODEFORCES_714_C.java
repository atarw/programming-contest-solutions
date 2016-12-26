import java.io.*;
import java.util.*;

public class CODEFORCES_714_C {
	
	static int [][] trie = new int [2000000][2], cnt = new int [2000000][2];
	static int insert = 1;
	
	public static void insert (String s) {
		int curr = 1, pos = 0;
		
		// insert 0s
		for (int i = 0; i < 18 - s.length (); ++i) {
			if (cnt [curr][pos] == 0)
				trie [curr][pos] = ++insert;
			
			++cnt [curr][pos];
			curr = trie [curr][pos];
		}
		
		//insert num
		for (int i = 0; i < s.length (); ++i) {
			pos = Character.getNumericValue (s.charAt (i)) & 1;
			
			if (cnt [curr][pos] == 0)
				trie [curr][pos] = ++insert;
			
			++cnt [curr][pos];
			curr = trie [curr][pos];
		}
	}
	
	public static void delete (String s) {
		int curr = 1, pos = 0;
		
		//delete 0s
		for (int i = 0; i < 18 - s.length (); ++i) {
			--cnt [curr][pos];
			curr = trie [curr][pos];
		}
		
		//delete num
		for (int i = 0; i < s.length (); ++i) {
			pos = Character.getNumericValue (s.charAt (i)) & 1;
			--cnt [curr][pos];
			curr = trie [curr][pos];
		}
	}
	
	public static int count (String s) {
		int curr = 1, pos = 0;
		
		//traverse 0s
		for (int i = 0; i < 18 - s.length (); ++i) {
			if (trie [curr][pos] == 0)
				return 0;

			curr = trie [curr][pos];
		}
		
		//traverse pattern
		for (int i = 0; i < s.length (); ++i) {
			pos = Character.getNumericValue (s.charAt (i)) & 1;
			
			if (trie [curr][pos] == 0)
				return 0;
			
			if (i != s.length () - 1)
				curr = trie [curr][pos];
		}
		
		return cnt [curr][pos];
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int Q = in.iscan (); in.cscan ();
		
		for (int q = 0, c; q < Q; ++q) {
			c = in.cscan ();
			
			if (c == '+')
				insert (in.sscan ());
			else if (c == '-')
				delete (in.sscan ());
			else
				out.println (count (in.sscan ()));
			
			if (q != Q - 1)
				in.cscan ();
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