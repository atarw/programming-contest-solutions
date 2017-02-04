import java.io.*;

public class CODEFORCES_758_A {
	public static void main (String [] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		t = in.readLine ().split (" ");
		
		int [] arr = new int [N];
		int max = -1;
		
		for (int n = 0; n < N; ++n) {
			arr [n] = Integer.parseInt (t [n]);
			max = Math.max (max, arr [n]);
		}
		
		int cnt = 0;
		
		for (int n = 0; n < N; ++n) {
			cnt += max - arr [n];
		}
		
		System.out.print (cnt);
	}
}