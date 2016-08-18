import java.io.*;

public class DMPG_15_B1_LONER_PHONER {
	
	public static String p (String a) {
		if (a.length () != 10 || !(a.substring (0, 3).equals ("647") || a.substring (0, 3).equals ("416"))) {
			return "not a phone number";
		}
		else {
			return "(" + a.substring (0, 3) + ")-" + a.substring (3, 6) + "-" + a.substring (6);
		}
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		
		for (int i = 0; i < N; i++) {
			System.out.println (p (in.readLine ()));
		}
	}
}