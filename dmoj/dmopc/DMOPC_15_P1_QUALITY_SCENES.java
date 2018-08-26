import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMOPC_15_P1_QUALITY_SCENES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int A = Integer.parseInt (in.readLine ());
		int B = Integer.parseInt (in.readLine ());
		int C = Integer.parseInt (in.readLine ());
		int D = Integer.parseInt (in.readLine ());

		if (B > C && A < D) {
			System.out.println ("YES");
		}
		else {
			System.out.println ("NO");
		}
	}
}