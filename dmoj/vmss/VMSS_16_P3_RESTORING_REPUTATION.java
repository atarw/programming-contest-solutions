import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VMSS_16_P3_RESTORING_REPUTATION {

	static int[][] cache;
	static int D, I, R;

	public static int edits (String N, String M) {
		if (cache[N.length ()][M.length ()] == 0 && !N.equals (M)) {
			if (N.isEmpty ()) {
				cache[N.length ()][M.length ()] = I * M.length ();
			}
			else if (M.isEmpty ()) {
				cache[N.length ()][M.length ()] = D * N.length ();
			}
			else {
				int replaceCost = N.charAt (0) == M.charAt (0) ? 0 : R;
				int min = edits (N.substring (1), M.substring (1)) + replaceCost;

				min = Math.min (min, edits (N, M.substring (1)) + I);//insert
				min = Math.min (min, edits (N.substring (1), M) + D);//delete

				cache[N.length ()][M.length ()] = min;
			}
		}

		return cache[N.length ()][M.length ()];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		D = Integer.parseInt (t[0]);
		I = Integer.parseInt (t[1]);
		R = Integer.parseInt (t[2]);

		t = in.readLine ().split (" ");

		cache = new int[t[0].length () + 1][t[1].length () + 1];

		System.out.println (edits (t[0], t[1]));
	}
}