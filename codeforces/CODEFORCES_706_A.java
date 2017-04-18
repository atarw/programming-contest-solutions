import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CODEFORCES_706_A {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int a = Integer.parseInt (t[0]), b = Integer.parseInt (t[1]);

		int N = Integer.parseInt (in.readLine ());
		int[][] taxi = new int[N][3];

		for (int n = 0; n < N; ++n) {
			t = in.readLine ().split (" ");

			for (int i = 0; i < 3; ++i)
				taxi[n][i] = Integer.parseInt (t[i]);
		}

		double min = Double.POSITIVE_INFINITY;

		for (int n = 0; n < N; ++n) {
			double dist = Math.pow (a - taxi[n][0], 2) + Math.pow (b - taxi[n][1], 2);
			min = Math.min (min, Math.sqrt (dist) / taxi[n][2]);
		}

		out.print (min);
		out.close ();
	}
}