import java.io.*;
import java.util.*;

public class CCC_14_S5_LAZY_FOX {
	
	static int [][] cache;
	static int [] x, y;
	
	public static int dist (int a, int b) {
		return (x [a] - x [b]) * (x [a] - x [b]) + (y [a] - y [b]) * (y [a] - y [b]);
	}
	
	public static int solve (int a, int b) {
		if (cache [a][b] != -1)
			return cache [a][b];
			
		int max = dist (a, b);
		cache [a][b] = 0;
		
		for (int n = 1; n < x.length; ++n)
			if (dist (b, n) < max)
				cache [a][b] = Math.max (cache [a][b], 1 + solve (b, n));
		
		return cache [a][b];
	}
	
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		
		cache = new int [N + 1][N + 1];
		x = new int [N + 1]; y = new int [N + 1];
		
		for (int i = 0; i < cache.length; ++i)
			for (int j = 0; j < cache [0].length; ++j)
				cache [i][j] = -1;
		
		for (int n = 1; n <= N; ++n) {
			t = in.readLine ().split (" ");
			x [n] = Integer.parseInt (t [0]); y [n] = Integer.parseInt (t [1]);
		}
		
		int max = -1;
		
		for (int n = 1; n <= N; ++n)
			max = Math.max (max, solve (0, n));
		
		System.out.print (max);
	}
}