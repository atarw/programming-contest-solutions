import java.io.*;
import java.util.*;

public class DMOPC_16_P4_TREE_APPRAISAL {
		
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int N = in.iscan ();
		int [] arr = new int [N];
		List <Integer> [] list = new ArrayList [N];
		
		for (int n = 0; n < N; ++n) {
			arr [n] = in.iscan ();
			list [n] = new ArrayList <Integer> ();
		}
		
		for (int n = 0, a, b; n < N - 1; ++n) {
			a = in.iscan () - 1; b = in.iscan () - 1;
			list [a].add (b); list [b].add (a);
		}

		long ans = 0;
		int [] dist, dist2;
		Queue <Integer> q;
		
		for (int n = 0, u; n < N; ++n) {
			q = new ArrayDeque <Integer> ();
			dist = new int [N];
			dist2 = new int [N];
			q.offer (n);
			
			Arrays.fill (dist, 1 << 20);
			Arrays.fill (dist2, 1 << 20);
			
			dist2 [n] = 0;
			dist [n] = arr [n];
			
			while (!q.isEmpty ()) {
				u = q.poll ();
				
				for (int v : list [u]) {
					if (dist [u] + arr [v] < dist [v]) {
						dist [v] = dist [u] + arr [v];
						dist2 [v] = dist2 [u] + 1;
						q.offer (v);
					}
				}
			}
			
			for (int n2 = 0; n2 < N; ++n2) {
				ans += dist [n2] * dist2 [n2];
			}
		}
		
		out.print (ans >> 1);
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