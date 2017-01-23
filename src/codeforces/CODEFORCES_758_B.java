import java.io.*;
import java.util.*;

public class B {
	static char [] ln;
	
	public static int find (char c) {
		for (int n = 0; n < ln.length; ++n) {
			if (ln [n] == c) {
				return n % 4;
			}
		}
		
		return -1;
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		ln = in.readLine ().toCharArray ();
		int r = find ('R'), b = find ('B'), g = find ('G'), y = find ('Y');
		
		int rcount = 0, bcount = 0, gcount = 0, ycount = 0;
		
		for (int n = 0; n < ln.length; ++n) {
			if (n % 4 == r && (ln [n] == '1' || ln [n] == '!'))
				++rcount;
			
			if (n % 4 == b && (ln [n] == '2' || ln [n] == '!'))
				++bcount;
			
			if (n % 4 == g && (ln [n] == '3' || ln [n] == '!'))
				++gcount;
			
			if (n % 4 == y && (ln [n] == '4' || ln [n] == '!'))
				++ycount;
		}
		
		System.out.println (rcount + " " + bcount + " " + ycount + " " + gcount);
	}
}