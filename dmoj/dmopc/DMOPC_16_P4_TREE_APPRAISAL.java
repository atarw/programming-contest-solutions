import java.io.*;
import java.util.*;

public class DMOPC_16_P4_TREE_APPRAISAL {
	
	static int N;
	static int [] arr;
	static List <Integer> [] list;
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		N = in.iscan ();
		arr = new int [N];
		list = new ArrayList [N];
		
		for (int n = 0; n < N; ++n)
			arr [n] = in.iscan ();
		
		for (int n = 0, a, b; n < N - 1; ++n) {
			a = in.iscan () - 1; b = in.iscan () - 1;
			
			if (list [a] == null)
				list [a] = new ArrayList <Integer> ();
			
			if (list [b] == null)
				list [b] = new ArrayList <Integer> ();
			
			list [a].add (b); list [b].add (a);
		}

		int [][] matrix = new int [N][N];
		int [][] matrix2 = new int [N][N];
		
		for (int n = 0; n < N; ++n) {
			Arrays.fill (matrix [n], 1 << 20);
			Arrays.fill (matrix2 [n], 1 << 20);
		}

		for (int n = 0; n < N; ++n) {
			Queue <Integer> q = new ArrayDeque <Integer> ();
			int curr = n;
			q.offer (curr);
			
			matrix [n][n] = arr [n];
			matrix2 [n][n] = 0;
			
			while (!q.isEmpty ()) {
				curr = q.poll ();
				
				for (int v = 0; v < list [curr].size (); ++v) {
					if (matrix [n][curr] + arr [list [curr].get (v)] < matrix [n][list [curr].get (v)]) {
						matrix [n][list [curr].get (v)] = matrix [n][curr] + arr [list [curr].get (v)];
						matrix2 [n][list [curr].get (v)] = matrix2 [n][curr] + 1;
						q.offer (list [curr].get (v));
					}
				}
			}
		}
		
		/*for (int [] arr : matrix)
			out.println (Arrays.toString (arr));
		
		out.println ();
		
		for (int [] arr : matrix2)
			out.println (Arrays.toString (arr));*/
		
		long ans = 0;

		for (int a = 0; a < N; ++a) {
			for (int b = a + 1; b < N; ++b) {
				ans += matrix2 [a][b] * matrix [a][b];
			}
		}

		out.print (ans);
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