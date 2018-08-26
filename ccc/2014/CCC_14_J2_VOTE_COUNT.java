import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_14_J2_VOTE_COUNT {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String votes = in.readLine ();

		int A = 0, B = 0;

		for (int i = 0; i < N; i++) {
			if (votes.charAt (i) == 'A') {
				A++;
			}
			else {
				B++;
			}
		}

		System.out.println (A > B ? "A" : A == B ? "Tie" : "B");
	}
}