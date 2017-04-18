import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_96_S1_DEFICIENT_PERFECT_AND_ABUNDANT {

	public static String status (int A) {
		int sum = 1;

		for (int i = 2; i < A; i++) {
			if (A % i == 0) {
				sum += i;
			}
		}

		if (sum == A) {
			return "perfect";
		}
		else if (sum < A) {
			return "deficient";
		}
		else {
			return "abundant";
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		int A;
		String status;

		for (int n = 0; n < N; n++) {
			A = Integer.parseInt (in.readLine ());
			status = status (A);
			System.out.println (A + " is a" + (status.charAt (0) == 'a' ? "n" : "") + " " + status + " number.");
		}
	}
}