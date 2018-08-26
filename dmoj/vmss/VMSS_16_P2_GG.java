import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VMSS_16_P2_GG {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String ln = in.readLine ();
		PrefixRunArray s = new PrefixRunArray (ln);

		int Q = Integer.parseInt (in.readLine ());
		String[] t;

		for (int q = 0; q < Q; q++) {
			t = in.readLine ().split (" ");

			System.out.println (s.sum (Integer.parseInt (t[0]), Integer.parseInt (t[1])));
		}
	}

	private static class PrefixRunArray {

		int[] arr;

		public PrefixRunArray (String ln) {
			arr = new int[ln.length () + 1];

			arr[0] = 0;

			for (int i = 1; i <= ln.length (); i++) {
				arr[i] = arr[i - 1] + (ln.charAt (i - 1) == 'G' ? 1 : 0);
			}
		}

		public int sum (int S, int E) {
			return arr[E + 1] - arr[S];
		}
	}
}