import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_13_J1_NEXT_IN_LINE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ());

		System.out.println (B * 2 - A);
	}
}