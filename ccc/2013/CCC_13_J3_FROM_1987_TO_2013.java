import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CCC_13_J3_FROM_1987_TO_2013 {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int Y = Integer.parseInt (in.readLine ());
		Set <Integer> set = new HashSet <Integer> (5);
		boolean good = true;

		for (int i = Y + 1; ; i++) {
			int copy = i;
			int len = ((Integer) copy).toString ().length ();
			for (int x = 0; x < len; x++) {
				if (!set.add (copy % 10)) {
					set.clear ();
					good = false;
					break;
				}
				copy = copy / 10;
			}

			if (good) {
				System.out.println (i);
				break;
			}
			else {
				good = true;
			}
		}
	}
}