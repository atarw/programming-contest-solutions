import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REN_ASHBELL {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		boolean good = true;
		int RA = Integer.parseInt (in.readLine ());

		for (int n = 1; n < N; n++) {
			if (Integer.parseInt (in.readLine ()) >= RA) {
				good = false;
				break;
			}
		}

		System.out.println (good ? "YES" : "NO");
	}
}