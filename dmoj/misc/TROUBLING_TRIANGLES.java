import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class TROUBLING_TRIANGLES {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());

		long[] x = new long[3], y = new long[3];//A, B, C
		long[][] sides = new long[3][2];//AB, AC, BC

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			x[0] = Long.parseLong (t[0]);
			x[1] = Long.parseLong (t[2]);
			x[2] = Long.parseLong (t[4]);
			y[0] = Long.parseLong (t[1]);
			y[1] = Long.parseLong (t[3]);
			y[2] = Long.parseLong (t[5]);

			sides[0][0] = x[1] - x[0];
			sides[0][1] = y[1] - y[0];
			sides[1][0] = x[2] - x[0];
			sides[1][1] = y[2] - y[0];
			sides[2][0] = x[2] - x[1];
			sides[2][1] = y[2] - y[1];

			System.out.printf ("%.2f %.2f\n", Math.sqrt (Math.pow (sides[0][0] * sides[1][1] - sides[0][1] *
					                   sides[1][0], 2)) * 0.5,
			                   Math.sqrt (Math.pow (sides[0][0], 2) + Math.pow (sides[0][1], 2)) + Math.sqrt (Math.pow
					                   (sides[1][0], 2) + Math.pow (sides[1][1], 2)) + Math.sqrt (Math.pow
					                   (sides[2][0], 2) + Math.pow (sides[2][1], 2)));
		}
	}
}