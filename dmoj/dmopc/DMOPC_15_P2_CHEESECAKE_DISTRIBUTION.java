import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMOPC_15_P2_CHEESECAKE_DISTRIBUTION {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		int N = Integer.parseInt (in.readLine ());
		long[] arr = new long[N];

		t = in.readLine ().split (" ");
		long sum = 0;

		for (int n = 0; n < N; n++) {
			arr[n] = Long.parseLong (t[n]);
			sum = sum + arr[n];
		}

		if (sum % N != 0) {
			System.out.print ("Impossible");
		}
		else {
			sum /= N;
			long time = 0;

			for (int n = 0; n < N; n++) {
				if (arr[n] > sum)
					time += (arr[n] - sum);
			}

			System.out.print (time);
		}
	}
}