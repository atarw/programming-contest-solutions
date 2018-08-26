import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_12_J1_SPEEDING_FINES_ARE_NOT_FINE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ());
		int diff = B - A;

		if (1 <= diff && diff <= 20) {
			System.out.println ("You are speeding and your fine is $100.");
		}
		else if (21 <= diff && diff <= 30) {
			System.out.println ("You are speeding and your fine is $270.");
		}
		else if (31 <= diff) {
			System.out.println ("You are speeding and your fine is $500.");
		}
		else {
			System.out.println ("Congratulations, you are within the speed limit!");
		}
	}
}