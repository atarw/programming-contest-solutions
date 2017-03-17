import java.io.*;
import java.util.*;

// supports: point/range update, point/range query all in O(logN) - everything is 1-indexed

public class BIT_RANGE {
	
	static int [] bit1, bit2;
	
	// adds v to element at pos
	public static void update_point (int pos, int v) {
		update_range (pos, pos, v); // probably is a better way to do this
	}
	
	// adds v to each element in [l, r]
	public static void update_range (int l, int r, int v) {
		update (bit1, l, v); update (bit1, r + 1, -v);
		update (bit2, l, v * (l - 1)); update (bit2, r + 1, -v * r);
	}
	
	// gets element at pos
	public static int query_point (int pos) {
		return query (bit1, pos);
	}
	
	// gets sum of elements in [l, r]
	public static int query_range (int l, int r) {
		return (query (bit1, r) * r - query (bit2, r)) - (query (bit1, l - 1) * (l - 1) - query (bit2, l - 1));
	}
	
	private static int query (int [] bit, int pos) {
		int ret = 0;
		
		for (; pos > 0; pos -= pos & -pos)
			ret += bit [pos];
		
		return ret;
	}
	
	private static void update (int [] bit, int pos, int v) {
		for (; pos < bit.length; pos += pos & -pos)
			bit [pos] += v;
	}
	
	public BIT_RANGE (int N) {
		bit1 = new int [N + 1];
		bit2 = new int [N + 1];
	}
}