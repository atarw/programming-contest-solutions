import java.io.*;
import java.util.*;
import java.text.*;

public class MEC_16_P1_BAT_DOCTOR {
	public static double solve (P p1, P p2) {
		return 90 + Math.atan ((p1.Y - p2.Y) / (p1.X - p2.X + 0.0)) * (180.0 / Math.PI);
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");
		String ln;
		
		int R = Integer.parseInt (t [0]), C = Integer.parseInt (t [1]);
		List <P> arr = new ArrayList <P> (C);
		
		for (int r = 0; r < R; r++) {
			ln = in.readLine ();
			
			for (int c = 0; c < C; c++) {
				if (ln.charAt (c) == 'X') {
					arr.add (new P (c, r));
				}
			}
		}
		
		if (arr.size () < 2) {
			System.out.println ("0.000");
		}
		else {
			Collections.sort (arr);
			DecimalFormat form = new DecimalFormat ("###.000");
			
			for (int a = 0; a < arr.size () - 1; a++) {
				System.out.println (form.format (solve (arr.get (a), arr.get (a + 1))));
			}
		}
	}
}

class P implements Comparable <P> {
	int X, Y;
	
	public int compareTo (P p) {
		return X - p.X;
	}
	
	public P (int X, int Y) {
		this.X = X;
		this.Y = Y;
	}
}