import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P1_CORE_DRILL {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		int r = Integer.parseInt (in.readLine ());
		int h = Integer.parseInt (in.readLine ());

		double v = Math.PI * r * r * h / 3;

		System.out.printf ("%.2f", v);
	}
}