import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CCC_07_J1_WHOS_IN_THE_MIDDLE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		List<Integer> a = new ArrayList<Integer> ();

		for (int i = 0; i < 3; i++) {
			a.add (Integer.parseInt (in.readLine ()));
		}
		//int A = Integer.parseInt (in.readLine ()), B = Integer.parseInt (in.readLine ()), C = Integer.parseInt (in
		// .readLine ());
		Collections.sort (a);

		System.out.println (a.get (1));

	}
}