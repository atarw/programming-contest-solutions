import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_04_J1_SQUARES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());

		System.out.println ("The largest square has side length " + (int) (Math.sqrt (N)) + ".");
	}
}