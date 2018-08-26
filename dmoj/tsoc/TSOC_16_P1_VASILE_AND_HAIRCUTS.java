import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TSOC_16_P1_VASILE_AND_HAIRCUTS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] t;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			int L = Integer.parseInt (t[0]);
			int A = Integer.parseInt (t[1]);
			int B = Integer.parseInt (t[2]);
			int F = Integer.parseInt (t[3]);

			int C = L - F;

			if (!(C < 0) && A <= C && C <= B) {
				System.out.println ("Yes");
			}
			else {
				System.out.println ("No");
			}
		}
	}
}