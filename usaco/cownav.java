import java.io.*;
import java.util.*;

public class cownav {
	
	static int N;
	static boolean [][] maze;
	static int [][][][][][] dp;
	static int [][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // up, right, down, left
	
	public static int rotate_left (int d) {
		return d == 0 ? 3 : d - 1;
	}
	
	public static int rotate_right (int d) {
		return (d + 1) % 4;
	}
	
	public static int [] move (int x, int y, int d) {
		int [] pt = {x, y};
		
		if (x == N - 1 && y == N - 1)
			return pt;
		
		if (d == 0)
			pt [1] = Math.min (N - 1, y + 1);
		else if (d == 1)
			pt [0] = Math.min (N - 1, x + 1);
		else if (d == 2)
			pt [1] = Math.max (0, y - 1);
		else
			pt [0] = Math.max (0, x - 1);
		
		if (!maze [pt [0]][pt [1]]) {
			pt [0] = x; pt [1] = y;
		}
		
		return pt;
	}
	
	public static int bfs () {
		dp = new int [N][N][N][N][4][4];
		
		for (int n = 0; n < N; ++n)
			for (int n2 = 0; n2 < N; ++n2)
				for (int n3 = 0; n3 < N; ++n3)
					for (int n4 = 0; n4 < N; ++n4)
						for (int n5 = 0; n5 < 4; ++n5)
							for (int n6 = 0; n6 < 4; ++n6)
								dp [n][n2][n3][n4][n5][n6] = 1 << 20;
		
		dp [0][0][0][0][1][0] = dp [0][0][0][0][0][1] = 0;
		
		State curr = new State (0, 0, 0, 0, 0, 1);
		Queue <State> q = new ArrayDeque <State> ();
		q.offer (curr); 
		
		int min = 1 << 20;
		
		while (!q.isEmpty ()) {
			curr = q.poll ();
			//System.out.println (curr + " -> " + dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2]);
			
			if (curr.x1 == N - 1 && curr.y1 == N - 1 && curr.x2 == N - 1 && curr.y2 == N - 1) {
				min = Math.min (min, dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2]);
			}
			else {
				// rotate left
				if (dp [curr.x1][curr.y1][curr.x2][curr.y2][rotate_left (curr.d1)][rotate_left (curr.d2)] > dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2] + 1) {
					dp [curr.x1][curr.y1][curr.x2][curr.y2][rotate_left (curr.d1)][rotate_left (curr.d2)] = dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2] + 1;
					q.offer (new State (curr.x1, curr.y1, curr.x2, curr.y2, rotate_left (curr.d1), rotate_left (curr.d2)));
				}
				
				// rotate right
				if (dp [curr.x1][curr.y1][curr.x2][curr.y2][rotate_right (curr.d1)][rotate_right (curr.d2)] > dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2] + 1) {
					dp [curr.x1][curr.y1][curr.x2][curr.y2][rotate_right (curr.d1)][rotate_right (curr.d2)] = dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2] + 1;
					q.offer (new State (curr.x1, curr.y1, curr.x2, curr.y2, rotate_right (curr.d1), rotate_right (curr.d2)));
				}
				
				// move forward
				int [] move1 = move (curr.x1, curr.y1, curr.d1), move2 = move (curr.x2, curr.y2, curr.d2);
				
				if (dp [move1 [0]][move1 [1]][move2 [0]][move2 [1]][curr.d1][curr.d2] > dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2] + 1) {
					dp [move1 [0]][move1 [1]][move2 [0]][move2 [1]][curr.d1][curr.d2] = dp [curr.x1][curr.y1][curr.x2][curr.y2][curr.d1][curr.d2] + 1;
					q.offer (new State (move1 [0], move1 [1], move2 [0], move2 [1], curr.d1, curr.d2));
				}
			}
		}
		
		return min;
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);
		
		/*INPUT in = new INPUT ("cownav.in");
		PrintWriter out = new PrintWriter (new File ("cownav.out"));*/

		N = in.iscan ();
		maze = new boolean [N][N];
		
		String ln;
		
		boolean [][] maze2 = new boolean [N][N];
		
		for (int n = 0; n < N; ++n) {
			ln = in.sscan ();
			
			for (int n2 = 0; n2 < N; ++n2)
				maze2 [n][n2] = ln.charAt (n2) == 'E';
		}
		// convert grid bc im lazy
		for (int n = 0; n < N; ++n)
			for (int n2 = 0; n2 < N; ++n2)
				maze [n][n2] = maze2 [N - n2 - 1][n];
				
		for (int n = 0; n < N; ++n) {
			for (int n2 = 0; n2 < N; ++n2)
				out.print (maze [n][n2] ? "E" : "H");
			out.println ();
		}
				
		out.print (bfs ());
		out.close ();		
	}
	
	private static class State {
		int x1, y1, x2, y2, d1, d2;
		
		public State (int x1, int y1, int x2, int y2, int d1, int d2) {
			this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2; this.d1 = d1; this.d2 = d2;
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