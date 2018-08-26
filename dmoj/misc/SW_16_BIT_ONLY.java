import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class SW_16_BIT_ONLY {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] t;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			long A = Integer.parseInt (t[0]);
			long B = Integer.parseInt (t[1]);
			long P = Long.parseLong (t[2]);

			System.out.println (A * B == P ? "POSSIBLE DOUBLE SIGMA" : "16 BIT S/W ONLY");
		}
	}
}