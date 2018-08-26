import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class MWC_15_C3_P2_MECHANICAL_PENCILS {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);

		int[] pencils = new int[N];
		int min = Integer.MAX_VALUE, index = 0;

		for (int n = 0; n < N; n++) {
			pencils[n] = Integer.parseInt (in.readLine ());

			if (pencils[n] < min) {
				min = pencils[n];
				index = n;
			}
		}

		int[] usable = new int[M];
		int[] pieces = new int[M];
		int lead, bestPack = 0, bestUse = Integer.MIN_VALUE;

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");
			pieces[m] = Integer.parseInt (t[0]);

			for (int i = 1; i < t.length; i++) {
				lead = Integer.parseInt (t[i]);
				if (lead > min) {
					usable[m] += lead;
				}
			}

			if (bestUse < usable[m] || bestUse == usable[m] && pieces[m] < pieces[bestPack]) {
				bestUse = usable[m];
				bestPack = m;
			}
		}

		System.out.println ((index + 1) + " " + (bestPack + 1));
	}
}