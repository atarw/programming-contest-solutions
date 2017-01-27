//apparently fenwick trees can do range updates...still don't know what was wrong with my segment tree solution...probably didn't do the math correctly :P

import java.io.*;
import java.util.*;

public class DMOPC_15_P6_LELEI_AND_CONTEST {
	
	static long [] bit1, bit2;
	static int M;
		
	public static void update_point (long [] bit, int pos, long val) {
		for (; pos < bit.length; pos += (pos & -pos)) {
			bit [pos] += val;
		}
	}
	
	public static void update_range (int l, int r, long val) {
		update_point (bit1, l, val); update_point (bit1, r + 1, -val);
		update_point (bit2, l, val * (l - 1)); update_point (bit2, r + 1, -val * r);
	}
	
	public static long query (long [] bit, int pos) {
		long ret = 0;
		
		for (; pos > 0; pos -= (pos & -pos)) {
			ret += bit [pos];
		}
		
		return ret;
	}
	
	public static long query (int n) {
		return query (bit1, n) * n - query (bit2, n);
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");
		
		M = Integer.parseInt (t [0]); int N = Integer.parseInt (t [1]), Q = Integer.parseInt (t [2]);
		bit1 = new long [N + 1]; bit2 = new long [N + 1];
				
		t = in.readLine ().split (" ");
		
		for (int n = 1; n <= N; ++n) {
			update_range (n, n, Integer.parseInt (t [n - 1]) % M);
		}
		
		int c, l, r, x;
				
		for (int q = 0; q < Q; ++q) {
			t = in.readLine ().split (" ");
			c = Integer.parseInt (t [0]); l = Integer.parseInt (t [1]); r = Integer.parseInt (t [2]);

			if (c == 1) {
				x = Integer.parseInt (t [3]) % M;
				update_range (l, r, x);
			}
			else {
				System.out.println ((query (r) - query (l - 1)) % M);
			}
		}
	}
}