import java.io.*;

public class CCC_15_J1_SPECIAL_DAY {
	
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int M = Integer.parseInt (in.readLine ());
		int D = Integer.parseInt (in.readLine ());
		
		if (M < 2 || M == 2 && D < 18) {
			System.out.println ("Before");
		}
		else if (M > 2 || M == 2 && D > 18) {
			System.out.println ("After");
		}
		else if (M == 2 && D == 18) {
			System.out.println ("Special");
		}
	}
}