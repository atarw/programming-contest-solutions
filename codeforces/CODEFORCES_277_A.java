import java.io.*;
import java.util.*;

public class CODEFORCES_277_A {
	
	static int [] parent;
	
	public static boolean union (int e, int e2) {
		int r1 = find (e), r2 = find (e2);
		
		if (r1 != r2) {
			parent [r1] = r2;
			find (e); find (e2);
			
			return true;
		}
		return false;
	}
	
	public static int find (int e) {
		if (parent [e] != e)
			parent [e] = find (parent [e]);
		
		return parent [e];
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);
		
		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t [0]), M = Integer.parseInt (t [1]), zero_count = 0;
		
		parent = new int [N];
		
		for (int n = 0; n < N; ++n)
			parent [n] = n;
		
		List <Integer> [] lp = new ArrayList [M];
		
		for (int m = 0; m < M; ++m)
			lp [m] = new ArrayList <Integer> ();
		
		for (int n = 0; n < N; ++n) {
			t = in.readLine ().split (" ");
			int K = Integer.parseInt (t [0]);
			
			if (K == 0)
				++zero_count;
			
			for (int k = 1; k <= K; ++k)
				lp [Integer.parseInt (t [k]) - 1].add (n);
		}
		
		if (zero_count == N) {
			out.print (N); out.close (); return;
		}
		
		int comp = N;
		
		for (int m = 0; m < M; ++m)
			for (int p = 0; p < lp [m].size () - 1; ++p)
				if (union (lp [m].get (p), lp [m].get (p + 1)))
					--comp;
		
		out.print (comp - 1);
		out.close ();
	}
}