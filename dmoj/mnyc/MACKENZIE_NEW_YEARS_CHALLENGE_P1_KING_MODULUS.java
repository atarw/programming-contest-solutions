import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MACKENZIE_NEW_YEARS_CHALLENGE_P1_KING_MODULUS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] FUCK_JUSTIN = in.readLine ().split (" ");
		int A = Integer.parseInt (FUCK_JUSTIN[0]);
		int B = Integer.parseInt (FUCK_JUSTIN[1]);

		int C = A % B;

		if (C < 0) C += B;

		System.out.println (C);
	}
}