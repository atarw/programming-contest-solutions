import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class FESTIVE_FARDIN {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String n = in.readLine ();
		String j = in.readLine ();

		Set<String> set = new HashSet<String> ();
		set.add ("green");
		set.add ("red");
		set.add ("white");

		if (set.contains (n) && set.contains (j)) {
			System.out.println ("Jingle Bells");
		}
		else {
			System.out.println ("Boring...");
		}
	}
}