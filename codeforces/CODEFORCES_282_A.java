import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_282_A {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		int X = 0;

		while (N-- > 0) {
			if (in.readLine ().contains ("+")) {
				++X;
			}
			else {
				--X;
			}
		}

		out.print (X);
		out.close ();
	}
}