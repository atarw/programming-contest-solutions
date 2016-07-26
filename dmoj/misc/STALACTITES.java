import java.io.*;

public class STALACTITES {
	
	public static long [][][] bit;
	public static int [][][] stalactite;
	
	public static void update (int x, int ori_y, int ori_z, long l) {
		for (; x < bit.length; x += x & -x) {
			for (int y = ori_y; y < bit.length; y += y & -y) {
				for (int z = ori_z; z < bit.length; z += z & -z) {
					bit[x][y][z] += l;
				}
			}
		}
	}
	
	public static long query (int x, int ori_y, int ori_z) {
		long ret = 0;
		
		for (; x > 0; x -= x & -x) {
			for (int y = ori_y; y > 0; y -= y & -y) {
				for (int z = ori_z; z > 0; z -= z & -z) {
					ret += bit [x][y][z];
				}
			}
		}		
		return ret;
	}
	
	public static long query (int x1, int y1, int z1, int x2, int y2, int z2) {
		return query (x2, y2, z2) - query (x1, y2, z2) - query (x2, y1, z2) + query (x1, y1, z2) /**/
		- query (x2, y2, z1) + query (x1, y2, z1) + query (x2, y1, z1) - query (x1, y1, z1);
	}
	
	public static void main (String [] t) throws IOException {
		INPUT in = new INPUT (System.in);
		PrintWriter out = new PrintWriter (System.out);
		
		int N = in.iscan (), Q = in.iscan (); ++Q;
		bit = new long [N + 1][N + 1][N + 1]; stalactite = new int [N][N][N];
		
		int cmd;
		int x1, x2, y1, y2, z1, z2, l;
		long total = 0L;
		
		while (--Q > 0) {
			cmd = in.cscan (); in.cscan ();
			
			if (cmd == 67) {//C
				x1 = in.iscan (); y1 = in.iscan (); z1 = in.iscan (); l = in.iscan ();
				update (x1, y1, z1, l - stalactite [x1 - 1][y1 - 1][z1 - 1]); stalactite [x1 - 1][y1 - 1][z1 - 1] = l;
			}
			else {//S
				x1 = in.iscan () - 1; y1 = in.iscan () - 1; z1 = in.iscan () - 1; x2 = in.iscan (); y2 = in.iscan (); z2 = in.iscan ();
				total += query (x1, y1, z1, x2, y2, z2);
			}
		}
		
		out.print (total);
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
				res *= 10;
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