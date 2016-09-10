import java.io.*;

public class JDCC_15_DECEMBER_D_SKIING {
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		int T = Integer.parseInt (in.readLine ());
		int MOD = 1000000007;
		
		while (T --> 0) {
			int N = Integer.parseInt (in.readLine ());
			char [][] maze = new char [N][N];
			long [][] cache = new long [N][N];
			
			for (int n = 0; n < N; ++n)
				maze [n] = in.readLine ().toCharArray ();
			
			for (int n = 0; n < N; ++n)
				cache [N - 1][n] = maze [N - 1][n] != 'X' ? 1 : 0;
				
			for (int n = N - 2; n >= 0; --n)
				for (int n2 = 0; n2 < N; ++n2)
					if (maze [n][n2] != 'X')
						cache [n][n2] +=
							(n2 - 1 >= 0 ? cache [n + 1][n2 - 1] : 0) + // left down
								(cache [n + 1][n2]) + // mid down
									(n2 + 1 < N ? cache [n + 1][n2 + 1] : 0); // right down
			
			long sum = 0;
			
			for (int n = 0; n < N; ++n)
				sum += cache [0][n];
			
			out.println (sum % MOD);
		}

		out.close ();
	}
}