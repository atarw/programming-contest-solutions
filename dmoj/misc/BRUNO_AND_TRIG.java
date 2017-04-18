import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRUNO_AND_TRIG {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int A = Integer.parseInt (in.readLine ());
		int B = Integer.parseInt (in.readLine ());
		int C = Integer.parseInt (in.readLine ());

		if (A + B <= C || A + C <= B || B + C <= A) {
			System.out.println ("Maybe I should go out to sea...");
		}
		else {
			System.out.println ("Huh? A triangle?");
		}
	}
}