import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CODEFORCES_1_A {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]), A = Integer.parseInt (t[2]);
		long ans = (long) (N / A + (N % A != 0 ? 1 : 0)) * (long) (M / A + (M % A != 0 ? 1 : 0));
		System.out.print (ans);
	}
}