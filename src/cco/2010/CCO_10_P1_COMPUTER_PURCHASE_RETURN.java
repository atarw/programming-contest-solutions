import java.io.*;
import java.util.*;

public class CCO_10_P1_COMPUTER_PURCHASE_RETURN {
	
	static List <Part> [] list;
	static int [][] cache;
	static int T;
	
	public static int solve (int t, int b) {
		if (cache [t][b] != -1)
			return cache [t][b];
		
		if (b == 0)
			if (t != T)
				return -(1 << 20);
			else
				return cache [t][b] = 0;
		
		if (t == T)
			return cache [t][b] = 0;
		
		for (Part p : list [t])
			if (p.C <= b)
				cache [t][b] = Math.max (cache [t][b], p.V + solve (t + 1, b - p.C));
		
		return cache [t][b];
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		T = Integer.parseInt (in.readLine ());
		int N = Integer.parseInt (in.readLine ());
		
		list = new ArrayList [T];
		
		for (int tt = 0; tt < T; ++tt)
			list [tt] = new ArrayList <Part> ();
		
		for (int n = 0, c, v, tt; n < N; ++n) {
			t = in.readLine ().split (" ");
			c = Integer.parseInt (t [0]); v = Integer.parseInt (t [1]); tt = Integer.parseInt (t [2]);
			list [tt - 1].add (new Part (c, v));
		}
		
		int B = Integer.parseInt (in.readLine ());
		cache = new int [T + 1][B + 1];
		
		for (int [] arr : cache)
			Arrays.fill (arr, -1);
		
		out.print (solve (0, B));
		out.close ();
	}
}

class Part {
	int C, V;
	
	public Part (int C, int V) {
		this.C = C; this.V = V;
	}
}