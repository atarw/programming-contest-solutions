import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DMOPC_14_P2_NOT_ENOUGH_REJUDGING {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		String[] codes = new String[N];
		int WAC = 0;

		for (int n = 0; n < N; n++) {
			codes[n] = in.readLine ();

			if (codes[n].equals ("WA")) {
				WAC++;
			}
		}

		WAC = (int) ((WAC / 100.00) * 30);
		int IRC = 0;

		for (int n = 0; n < N; n++) {
			if (codes[n].equals ("AC")) {
				System.out.println (codes[n]);
			}
			else if (codes[n].equals ("WA")) {
				if (WAC > 0) {
					System.out.println ("AC");
					WAC--;
				}
				else {
					System.out.println ("WA");
				}
			}
			else if (codes[n].equals ("IR")) {
				if (IRC < 10) {
					System.out.println ("AC");
					IRC++;
				}
				else if (IRC < 20) {
					System.out.println ("WA");
					IRC++;
				}
				else {System.out.println (codes[n]);}
			}
			else {
				System.out.println ("WA");
			}
		}
	}
}