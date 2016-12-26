import java.io.*;
import java.util.*;

public class CCC_02_S4_BRIDGE_CROSSING {
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);

		int M = in.iscan (), Q = in.iscan ();
		
		int [] time = new int [Q];
		String [] name = new String [Q];
		
		for (int q = 0; q < Q; ++q) {
			name [q] = in.sscan (); time [q] = in.iscan ();
		}
		
		State [][] dp = new State [Q + 1][M + 1]; // q_pos, curr group size
		Queue <State> q = new ArrayDeque <State> ();

		dp [0][1] = new State (-1, -1, 0, 1, 0, 0, time [0]);
		State curr = dp [0][1];
		q.offer (curr);
		
		State end = null;
		
		while (!q.isEmpty ()) {
			curr = q.poll ();
			
			if (curr.q_pos == Q - 1) {
				if (end == null || curr.time + curr.group_time < end.time + end.group_time) {
					end = curr;
				}
			}
			else {
				if (dp [curr.q_pos + 1][1] == null || dp [curr.q_pos + 1][1].time + dp [curr.q_pos + 1][1].group_time > curr.time + curr.group_time + time [curr.q_pos + 1]) {
					dp [curr.q_pos + 1][1] = new State (curr.q_pos, curr.members, curr.group + 1, 1, curr.q_pos + 1, curr.time + curr.group_time, time [curr.q_pos + 1]);
					q.offer (dp [curr.q_pos + 1][1]);
				}
				
				if (curr.members + 1 <= M) {
					if (dp [curr.q_pos + 1][curr.members + 1] == null || dp [curr.q_pos + 1][curr.members + 1].time + dp [curr.q_pos + 1][curr.members + 1].group_time > curr.time + Math.max (curr.group_time, time [curr.q_pos + 1])) {
						dp [curr.q_pos + 1][curr.members + 1] = new State (curr.q_pos, curr.members, curr.group, curr.members + 1, curr.q_pos + 1, curr.time, Math.max (curr.group_time, time [curr.q_pos + 1]));
						q.offer (dp [curr.q_pos + 1][curr.members + 1]);
					}
				}
			}
		}
		
		List <String> [] groups = new ArrayList [end.group + 1];
		
		for (int g = 0; g < groups.length; ++g)
			groups [g] = new ArrayList <String> ();
		
		curr = end;
		
		while (true) {
			groups [curr.group].add (name [curr.q_pos]);
			
			if (curr.px == -1)
				break;
			
			curr = dp [curr.px][curr.py];
		}
				
		out.println ("Total Time: " + (end.time + end.group_time));
		
		for (int g = 0; g < groups.length; ++g) {
			for (String n : groups [g]) {
				out.print (n + " ");
			}
			out.println ();
		}
		
		out.close ();
	}
	
	private static class State {
		int px, py;
		int group, members, q_pos, time, group_time;
		
		public State (int px, int py, int group, int members, int q_pos, int time, int group_time) {
			this.px = px;
			this.py = py;
			this.group = group;
			this.members = members;
			this.q_pos = q_pos;
			this.time = time;
			this.group_time = group_time;
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