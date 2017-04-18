import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_07_S1_FEDERAL_VOTING_AGE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());

		for (int n = 0; n < N; n++) {
			String[] t = in.readLine ().split (" ");
			int y = Integer.parseInt (t[0]);
			int m = Integer.parseInt (t[1]);
			int d = Integer.parseInt (t[2]);

			boolean old = false;

			if (1989 > y || 1989 == y && (m < 2 || m == 2 && d <= 27)) {
				old = true;
			}

			System.out.println (old ? "Yes" : "No");
		}
	}
}