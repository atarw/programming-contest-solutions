import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DMOPC_14_P4_DEFORESTATION {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int R = Integer.parseInt (in.readLine ());
		int[] arr = new int[R + 1];

		for (int r = 1; r <= R; r++) {
			arr[r] = arr[r - 1] + Integer.parseInt (in.readLine ());
		}

		int Q = Integer.parseInt (in.readLine ());

		for (int q = 0; q < Q; q++) {
			t = in.readLine ().split (" ");
			System.out.println (arr[Integer.parseInt (t[1]) + 1] - arr[Integer.parseInt (t[0])]);
		}
	}
}