import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class SHIP {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Set<Character> set = new HashSet<Character> ();
		set.add ('B');
		set.add ('F');
		set.add ('T');
		set.add ('L');
		set.add ('C');

		char[] arr = in.readLine ().toCharArray ();

		for (Character i : arr) {
			set.remove (i);
		}

		if (!set.isEmpty ()) {
			for (Character i : set) {
				System.out.print (i);
			}
		}
		else {
			System.out.println ("NO MISSING PARTS");
		}
	}
}