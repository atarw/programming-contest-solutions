import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CODEFORCES_158_A {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int N = Integer.parseInt (t[0]), K = Integer.parseInt (t[1]) - 1;

		t = in.readLine ().split (" ");
		int[] arr = new int[N];

		int count = 0;

		for (int n = 0; n < N; ++n) {
			arr[n] = Integer.parseInt (t[n]);
		}

		for (int n = 0; n < N; ++n) {
			if (arr[n] > 0 && arr[n] >= arr[K]) {
				++count;
			}
		}

		out.print (count);
		out.close ();
	}
}