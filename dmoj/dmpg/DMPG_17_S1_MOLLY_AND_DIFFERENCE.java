import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

// atharva washimkar
// May 15, 2017

public class DMPG_17_S1_MOLLY_AND_DIFFERENCE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		long [] arr = new long [N];
		t = in.readLine ().split (" ");

		for (int n = 0; n < N; ++n)
			arr [n] = Long.parseLong (t [n]);

		Arrays.sort (arr);
		long min = Long.MAX_VALUE;

		for (int n = 0; n < N - 1; ++n)
			min = Math.min (min, arr [n + 1] - arr [n]);

		out.print (min);
		out.close ();
	}
}