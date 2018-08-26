import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CODEFORCES_4_A {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int W = Integer.parseInt (in.readLine ());

		if (W % 2 != 0 || W == 2) {
			System.out.print ("NO");
		}
		else {
			System.out.print ("YES");
		}
	}
}