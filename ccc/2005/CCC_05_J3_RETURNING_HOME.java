import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class CCC_05_J3_RETURNING_HOME {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		LinkedList <String> list = new LinkedList <String> ();
		String t;
		String dir = "";

		while (true) {
			t = in.readLine ();
			list.addFirst (t);

			if (t.equals ("SCHOOL")) {
				break;
			}
		}

		for (int i = 0; i < list.size () - 1; i += 2) {
			if (!list.get (i).equals ("SCHOOL")) {
				if (list.get (i).equals ("R")) {
					dir = "LEFT";
				}
				else if (list.get (i).equals ("L")) {
					dir = "RIGHT";
				}
				System.out.println ("Turn " + dir + " onto " + list.get (i + 1) + " street.");
			}
			else {
				i--;
			}
		}
		dir = (list.get (list.size () - 1).equals ("R") ? "LEFT" : "RIGHT");

		System.out.println ("Turn " + dir + " into your HOME.");
	}
}