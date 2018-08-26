import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_231_A {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());

		int count = 0, sum = 0;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			sum = 0;

			for (int i = 0; i < 3; i++) {
				sum += Integer.parseInt (t[i]);
			}

			if (sum > 1) count++;
		}

		out.print (count);
		out.close ();
	}
}