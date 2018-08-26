import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FIZZ_FUZZ {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());

		int X = 0, Y = -1;

		for (int i = 0; i < N; i++) {
			X++;
			Y += 2;

			if (X % 7 == 0 && X % 13 == 0) {
				System.out.print ("Fizz Fuzz");
			}
			else if (X % 7 == 0) {
				System.out.print ("Fizz");
			}
			else if (X % 13 == 0) {
				System.out.print ("Fuzz");
			}
			else {
				System.out.print (X);
			}

			System.out.print (" ");

			if (Y % 7 == 0 && Y % 13 == 0) {
				System.out.print ("Fizz Fuzz");
			}
			else if (Y % 7 == 0) {
				System.out.print ("Fizz");
			}
			else if (Y % 13 == 0) {
				System.out.print ("Fuzz");
			}
			else {
				System.out.print (Y);
			}

			System.out.println ();
		}
	}
}