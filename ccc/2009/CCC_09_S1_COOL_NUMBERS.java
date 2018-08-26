import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_09_S1_COOL_NUMBERS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ());
		int count = 0;

		for (int i = 1; i <= B; i++) {
			int p = (int) Math.pow (i, 6);

			if (A <= p && p <= B) {
				count++;
			}
			else if (p > B) {
				break;
			}
		}

		System.out.println (count);
	}
}