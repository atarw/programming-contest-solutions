import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_12_S1_DONT_PASS_ME_THE_BALL {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());

		if (N < 4) {
			System.out.println (0);
		}
		else {
			System.out.println ((N - 1) * (N - 2) * (N - 3) / 6);
		}
	}
}