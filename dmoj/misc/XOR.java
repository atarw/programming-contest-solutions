import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class XOR {

	public static int val (int a) {
		switch (a % 4) {
			case 0:
				return a;
			case 1:
				return 1;
			case 2:
				return a + 1;
			default:
				return 0;
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int T = Integer.parseInt (in.readLine ());
		String[] tokens;

		for (int i = 0; i < T; i++) {
			tokens = in.readLine ().split (" ");
			System.out.println (val (Integer.parseInt (tokens[1])) ^ val (Integer.parseInt (tokens[0]) - 1));
		}
	}
}