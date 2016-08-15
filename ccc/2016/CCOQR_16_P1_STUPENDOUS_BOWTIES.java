import java.io.*;
import java.util.*;

public class CCOQR_16_P1_STUPENDOUS_BOWTIES {
			
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		
		P [] x = new P [N], y = new P [N];
		
		for (int n = 0; n < N; ++n) {
			t = in.readLine ().split (" ");
			x [n] = new P (Integer.parseInt (t [0]), Integer.parseInt (t [1]));
			y [n] = new P (x [n].y, x [n].x);
		}
		
		Arrays.sort (x); Arrays.sort (y);
		
		long total = 0;
		long left = 0, right = 0, up = 0, down = 0;
		
		for (int n = 0; n < N; ++n) {
			left = Arrays.binarySearch (y, new P (x [n].y, x [n].x)) - -(Arrays.binarySearch (y, new P (x [n].y, Integer.MIN_VALUE)) + 1); // search for pos of coordinate (min_x_value, y) to get all left of (x, y)
			right = -(Arrays.binarySearch (y, new P (x [n].y, Integer.MAX_VALUE)) + 1) - Arrays.binarySearch (y, new P (x [n].y, x [n].x)) - 1; // search for pos of coordinate (max_x_value, y) to get all right of (x, y)
			up = -(Arrays.binarySearch (x, new P (x [n].x, Integer.MAX_VALUE)) + 1) - n - 1; // search for pos of coordinate (x, max_y_value) to get all above (x, y)
			down = n - -(Arrays.binarySearch (x, new P (x [n].x, Integer.MIN_VALUE)) + 1); // search for pos of coordinate (x, min_y_value) to get all below (x, y)
			
			total += up * right * down * left * 2;
		}
		
		out.print (total);
		out.close ();
	}
}

class P implements Comparable <P> {
	int x, y;
	
	public int compareTo (P p) {
		if (this.x < p.x)
			return -1;
			
		else if (this.x > p.x)
			return 1;
			
		else if (this.y < p.y)
			return -1;
			
		else if (this.y > p.y)
			return 1;
			
		else
			return 0;
	}
	
	public P (int x, int y) {
		this.x = x; this.y = y;
	}
}