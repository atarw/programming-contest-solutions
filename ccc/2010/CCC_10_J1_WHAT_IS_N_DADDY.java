import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_10_J1_WHAT_IS_N_DADDY {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int n = Integer.parseInt (in.readLine ());

		if (n == 1 || n == 9 || n == 10) System.out.println (1);
		else if (n == 2 || n == 3 || n == 7 || n == 8) System.out.println (2);
		else System.out.println (3);
	}
}