import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// atharva washimkar
// August 11, 2018

public class ECOO_16_R1_P2_SPINDIE {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N;
		Set<Integer> spin;
		int[] targets = new int[5];

		for (int i = 0; i < 10; i++) {
			N = Integer.parseInt (in.readLine ());
			spin = new HashSet<Integer> (100);

			t = in.readLine ().split (" ");

			for (int n = 0; n < N; n++) {
				spin.add (Integer.parseInt (t[n]));
			}

			t = in.readLine ().split (" ");

			for (int j = 0; j < 5; j++) {
				targets[j] = Integer.parseInt (t[j]);
			}

			int found = 0;

			//t = n1 + n2 + n3
			//t = n1 * n2 + n3
			//t = (n1 + n2) * n3
			//t = n1 * n2 * n3

			out:
			for (int n1 : spin) {
				for (int n2 : spin) {
					for (int f = 0; f < 5; f++) {
						if (targets[f] != -1 && spin.contains (targets[f] - n1 - n2) || spin.contains (targets[f] - n1
								* n2) || targets[f] % (n1 + n2) == 0 && spin.contains (targets[f] / (n1 + n2)) ||
								targets[f] % (n1 * n2) == 0 && spin.contains (targets[f] / (n1 * n2))) {
							targets[f] = -1;
							found++;

							if (found == 5) break out;
						}
					}
				}
			}

			for (int f = 0; f < 5; f++) {
				System.out.print (targets[f] == -1 ? 'T' : 'F');
			}
			System.out.println ();
		}
	}
}