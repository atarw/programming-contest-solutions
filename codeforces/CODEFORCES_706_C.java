import java.io.*;
import java.util.*;

public class CODEFORCES_706_C {
	
	static long [][] dp;
	static int [] price;
	static String [] arr;
	static String [] rev;
	
	static long DEF = 1L << 62;
	
	public static long solve (int n, int l) {
		if (n == -1)
			return 0;
		
		if (dp [n][l] != DEF)
			return dp [n][l];
		
		if (l == 1) {
			if (arr [n].compareTo (rev [n + 1]) <= 0)
				dp [n][l] = Math.min (dp [n][l], solve (n - 1, 0));
			
			if (rev [n].compareTo (rev [n + 1]) <= 0)
				dp [n][l] = Math.min (dp [n][l], price [n] + solve (n - 1, 1));
		}
		else {
			if (arr [n].compareTo (arr [n + 1]) <= 0)
				dp [n][l] = Math.min (dp [n][l], solve (n - 1, 0));
			
			if (rev [n].compareTo (arr [n + 1]) <= 0)
				dp [n][l] = Math.min (dp [n][l], price [n] + solve (n - 1, 1));
		}
		
		return dp [n][l];
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int N = Integer.parseInt (in.readLine ());
		dp = new long [N][2];
		price = new int [N];
		arr = new String [N];
		rev = new String [N];
		
		t = in.readLine ().split (" ");
		
		for (int n = 0; n < N; ++n)
			price [n] = Integer.parseInt (t [n]);
		
		for (int n = 0; n < N; ++n) {
			arr [n] = in.readLine ();
			rev [n] = new StringBuilder (arr [n]).reverse ().toString ();
		}
		
		for (int n = 0; n < N; ++n)
			dp [n][0] = dp [n][1] = DEF;
		
		long min = Math.min (solve (N - 2, 0), price [N - 1] + solve (N - 2, 1));
		
		out.print (min == DEF ? -1 : min);
		out.close ();
	}
}