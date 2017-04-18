import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SEGMENT_TREE_TEST_ARRAYS {
	
	static int [] l_arr, r_arr, min_arr, gcd_arr, count_arr;
	
	static int [] arr;
	static int SIZE;

	public static int gcd (int a, int b) {
		return b == 0 ? a : gcd (b, a % b);
	}
	
	public static int l_child (int pos) {
		return 2 * pos + 1;
	}
	
	public static int r_child (int pos) {
		return 2 * pos + 2;
	}
	
	public static void merge_range (int pos) {
		l_arr [pos] = l_arr [l_child (pos)]; r_arr [pos] = r_arr [r_child (pos)];
		min_arr [pos] = Math.min (min_arr [l_child (pos)], min_arr [r_child (pos)]);
		gcd_arr [pos] = gcd (gcd_arr [l_child (pos)], gcd_arr [r_child (pos)]);
		count_arr [pos] = 0;

		if (gcd_arr [pos] == gcd_arr [l_child (pos)]) {
			count_arr [pos] += count_arr [l_child (pos)];
		}
		
		if (gcd_arr [pos] == gcd_arr [r_child (pos)]) {
			count_arr [pos] += count_arr [r_child (pos)];
		}
	}
	
	public static void build (int pos, int l, int r) {
		l_arr [pos] = l; r_arr [pos] = r;
		
		if (l == r) {
			min_arr [pos] = gcd_arr [pos] = arr [l];
			count_arr [pos] = 1;
		}
		else {
			int mid = (l + r) / 2;

			build (l_child (pos), l, mid);
			build (r_child (pos), mid + 1, r);
			
			merge_range (pos);
		}
	}
	
	public static void update (int t_pos, int a_pos, int v) {
		if (l_arr [t_pos] == a_pos && r_arr [t_pos] == a_pos) {
			min_arr [t_pos] = gcd_arr [t_pos] = v;
			count_arr [t_pos] = 1;
		}
		else {
			int mid = (l_arr [t_pos] + r_arr [t_pos]) / 2;
			
			if (a_pos <= mid) {
				update (l_child (t_pos), a_pos, v);
			}
			else {
				update (r_child (t_pos), a_pos, v);
			}
			
			merge_range (t_pos);
		}
	}
	
	public static int query_min (int pos, int l, int r) {
		if (l == l_arr [pos] && r == r_arr [pos]) {
			return min_arr [pos];
		}
		
		int mid = (l_arr [pos] + r_arr [pos]) / 2;
		
		if (r <= mid) {//if range is completely in left child
			return query_min (l_child (pos), l, r);
		}
		else if (l > mid) {//if range is completely in right child
			return query_min (r_child (pos), l, r);
		}
		
		//if range spans across both children
		return Math.min (query_min (l_child (pos), l, mid), query_min (r_child (pos), mid + 1, r));
	}
	
	public static int query_gcd (int pos, int l, int r) {
		if (l == l_arr [pos] && r == r_arr [pos]) {
			return gcd_arr [pos];
		}
		
		int mid = (l_arr [pos] + r_arr [pos]) / 2;
		
		if (r <= mid) {//if range is completely in left child
			return query_gcd (l_child (pos), l, r);
		}
		else if (l > mid) {//if range is completely in right child
			return query_gcd (r_child (pos), l, r);
		}
		
		//if range spans across both children
		return gcd (query_gcd (l_child (pos), l, mid), query_gcd (r_child (pos), mid + 1, r));
	}
	
	public static int query_count (int pos, int l, int r) {//tree pos, query range
		if (l_arr [pos] == l && r_arr [pos] == r) {
			return count_arr [pos];
		}
		
		int mid = (l_arr [pos] + r_arr [pos]) / 2;
		
		if (r <= mid) {
			return query_count (l_child (pos), l, r);
		}
		else if (l > mid) {
			return query_count (r_child (pos), l, r);
		}
		
		int l_gcd = query_gcd (l_child (pos), l, mid), r_gcd = query_gcd (r_child (pos), mid + 1, r), p_gcd = gcd (l_gcd, r_gcd);
		int l_count = query_count (l_child (pos), l, mid), r_count = query_count (r_child (pos), mid + 1, r), p_count = 0;
		
		if (p_gcd == l_gcd) {
			p_count += l_count;
		}
		
		if (p_gcd == r_gcd) {
			p_count += r_count;
		}

		return p_count;
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]);
		double height = Math.log (N) / Math.log (2); SIZE = (int) Math.ceil (Math.pow (2, height + 2));//SIZE set larger than needed
		
		arr = new int [N]; l_arr = new int [SIZE]; r_arr = new int [SIZE]; min_arr = new int [SIZE]; gcd_arr = new int [SIZE]; count_arr = new int [SIZE];
		
		t = in.readLine ().split (" ");
		
		for (int n = 0; n < N; ++n) {
			arr [n] = Integer.parseInt (t [n]);
		}
		
		build (0, 0, N - 1);
		
		int a, b;
		
		for (int m = 0; m < M; ++m) {
			t = in.readLine ().split (" ");
			a = Integer.parseInt (t [1]) - 1; b = Integer.parseInt (t [2]) - 1;
			
			if (t [0].charAt (0) == 'C') {
				update (0, a, b + 1);
			}
			else if (t [0].charAt (0) == 'M') {
				out.println (query_min (0, a, b));
			}
			else if (t [0].charAt (0) == 'G') {
				out.println (query_gcd (0, a, b));
			}
			else {
				out.println (query_count (0, a, b));
			}
		}
		
		out.close ();
	}
}