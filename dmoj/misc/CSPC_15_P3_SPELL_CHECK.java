import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CSPC_15_P3_SPELL_CHECK {

	public static String correct (String w, int i) {
	/*int d1 = w.indexOf ("ie");
	 int d2 = w.indexOf ("ei");
     int d3 = w.indexOf ("cei");
     int d4 = w.indexOf ("cie");*/


		if ((w.contains ("cei") || w.contains ("ie")) && !w.contains ("cie")) {
			return "Word " + i + " is correct.";
		}
		if (w.contains ("cie")) {
			//System.out.println ("MATCHES CIE");
			w = w.replaceAll ("cie", "cei");
		}
		if (w.contains ("ei") && !w.contains ("cei")) {
			//System.out.println ("MATCHES EI - NOT CEI");
			w = w.replaceAll ("ei", "ie");
		}

		return w;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String input = in.readLine ();
		int i = 1;

		while (!input.equals ("No More Words!")) {
			System.out.println (correct (input, i));
			i++;
			input = in.readLine ();
		}
	}
}