import java.io.*;
import java.util.*;

public class CCC_16_S1_RAGAMAN {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		StringBuilder ln = new StringBuilder (in.readLine ()), ln2 = new StringBuilder (in.readLine ());
		
		for (int i = ln.length () - 1; i >= 0; i--) {
			if (ln2.indexOf (ln.charAt (i) + "") != -1) {
				ln2.deleteCharAt (ln2.indexOf (ln.charAt (i) + ""));
				ln.deleteCharAt (i);
			}
		}
		
		//System.out.println (ln + "\n" + ln2);
		
		for (int i = ln.length () - 1; i >= 0; i--) {
			if (ln.charAt (i) == '*') {
				ln2.deleteCharAt (ln2.length () - 1);
				ln.deleteCharAt (i);
			}
		}
		
		for (int i = ln2.length () - 1; i >= 0; i--) {
			if (ln2.charAt (i) == '*') {
				ln.deleteCharAt (ln.length () - 1);
				ln2.deleteCharAt (i);
			}
		}
		
		// System.out.println (ln + "\n" + ln2);
		
		if (ln.length () == 0 && ln2.length () == 0) {
			System.out.println ("A");
		}
		else {
			System.out.println ("N");
		}
	}
}