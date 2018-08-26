import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P5_SAWMILL_SCHEME {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), A, B;
		List<Integer>[] list = new ArrayList[N];
		double[] cache = new double[N];

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			A = Integer.parseInt (t[0]) - 1;
			B = Integer.parseInt (t[1]) - 1;

			if (list[A] == null) {
				list[A] = new ArrayList<Integer> ();
			}

			list[A].add (B);
		}

		cache[0] = 1;

		for (int n = 0; n < N; n++) {
			if (list[n] != null) {
				for (int i = 0; i < list[n].size (); i++) {
					cache[list[n].get (i)] += cache[n] / list[n].size ();
				}
			}
			else {
				System.out.println (cache[n]);
			}
		}
	}
}