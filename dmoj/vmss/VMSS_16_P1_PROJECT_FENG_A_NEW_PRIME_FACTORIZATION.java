import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class VMSS_16_P1_PROJECT_FENG_A_NEW_PRIME_FACTORIZATION {

	public static void primes (int M) {
		for (int i = 2; i <= M; i++) {
			if (M % i == 0) {
				System.out.println (i);
				M /= i;
				i--;
			}
		}
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int M = Integer.parseInt (in.readLine ());

		primes (M);
	}
}