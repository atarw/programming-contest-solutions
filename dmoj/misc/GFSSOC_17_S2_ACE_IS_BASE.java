import java.io.*;
import java.util.*;

public class GFSSOC_17_S2_ACE_IS_BASE {
	
	public static char get (int n) {
		if (n == 0)
			return '0';
		
		if (n == 1)
			return 'A';
		
		if (n == 2)
			return 'C';
		
		if (n == 3)
			return 'E';
		
		return 'L';
	}
	
	public static int lower_bound (List <Long> list, long x) {
		int low = 0, high = list.size (), mid = -1;
		
		while (low < high) {
			mid = (low + high) / 2;
			
			if (list.get (mid) >= x)
				high = mid;
			else
				low = mid + 1;
		}
		
		return low;
	}
	
	public static int upper_bound (List <Long> list, long x) {
		int low = 0, high = list.size (), mid = -1;
		
		while (low < high) {
			mid = (low + high) / 2;
			
			if (list.get (mid) > x)
				high = mid;
			else
				low = mid + 1;
		}
		
		return low;
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		long a = Long.parseLong (in.readLine ());
		long b = Long.parseLong (in.readLine ());
		
		// goes up to 10^10, that means longest will have 9 hex digits
		List <Long> list = new ArrayList <Long> ();
		String val;
		
		for (int n = 0; n <= 3; ++n) {
			for (int n2 = n == 0 ? 0 : 1; n2 <= 3; ++n2) {
				for (int n3 = n2 == 0 ? 0 : 1; n3 <= 3; ++n3) {
					for (int n4 = n3 == 0 ? 0 : 1; n4 <= 3; ++n4) {
						for (int n5 = n4 == 0 ? 0 : 1; n5 <= 3; ++n5) {
							for (int n6 = n5 == 0 ? 0 : 1; n6 <= 3; ++n6) {
								for (int n7 = n6 == 0 ? 0 : 1; n7 <= 3; ++n7) {
									for (int n8 = n7 == 0 ? 0 : 1; n8 <= 3; ++n8) {
										for (int n9 = 1; n9 <= 3; ++n9) {
											val = "" + get (n) + get (n2) + get (n3) + get (n4) + get (n5) + get (n6) + get (n7) + get (n8) + get (n9);
											list.add (Long.parseLong (val, 16));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		Collections.sort (list);
		int p2 = upper_bound (list, b), p1 = lower_bound (list, a);
		
		//out.println (list);
		//out.println (p1 + " " + p2);
		out.println (p2 - p1);
		out.close ();
	}
}