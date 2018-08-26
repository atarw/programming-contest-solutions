import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class NEXT_PRIME {

	public static boolean isPrime (int num) {
		if (num != 2 && (num % 2 == 0 || num <= 1)) {
			return false;
		}

		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int H = Integer.parseInt (in.readLine ());

		for (int i = H; ; i++) {
			if (isPrime (i)) {
				System.out.println (i);
				break;
			}
		}
	}
}