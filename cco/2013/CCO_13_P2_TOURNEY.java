import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class CCO_13_P2_TOURNEY {

	static int[] tree;//stores index of competitors
	static int[] arr;//stores values
	static int powN;

	public static int win () {
		return tree[0] + 1;
	}

	public static void replace (int i, int S) {
		arr[i] = S;
		build (powN - 1 + i);
	}

	public static int rounds (int curr) {
		int score = 0;

		for (int i = curr; i > 0; ) {
			if (i % 2 == 0 && tree[(i - 1) / 2] == tree[i]) {
				score++;
				i = (i - 1) / 2;
			}
			else if (i % 2 != 0 && tree[i / 2] == tree[i]) {
				score++;
				i = i / 2;
			}
			else {
				break;
			}
		}

		return score;
	}

	public static void build (int curr) {
		for (int i = curr; i > 0; ) {
			if (i == 0) {
				break;
			}

			if (i % 2 == 0) {
				if (arr[tree[i]] > arr[tree[i - 1]]) {
					tree[(i - 1) / 2] = tree[i];
				}
				else {
					tree[(i - 1) / 2] = tree[i - 1];
				}
				i = (i - 1) / 2;
			}
			else if (i % 2 != 0) {
				if (arr[tree[i]] > arr[tree[i + 1]]) {
					tree[i / 2] = tree[i];
				}
				else {
					tree[i / 2] = tree[i + 1];
				}
				i = i / 2;
			}
			else {
				break;
			}
		}
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), M = Integer.parseInt (t[1]);
		powN = 1 << N;

		tree = new int[powN * 2 - 1];
		arr = new int[powN];

		Arrays.fill (tree, -1);

		for (int n = 0; n < powN; n++) {
			arr[n] = Integer.parseInt (in.readLine ());
			tree[powN - 1 + n] = n;
		}

		for (int n = powN - 1; n < tree.length; n++) {
			for (int i = n; i > 0; ) {
				if (i % 2 == 0) {
					if (tree[(i - 1) / 2] == -1 || arr[tree[(i - 1) / 2]] < arr[tree[i]]) {
						tree[(i - 1) / 2] = tree[i];
					}
					i = (i - 1) / 2;
				}
				else if (i % 2 != 0) {
					if (tree[i / 2] == -1 || arr[tree[i / 2]] < arr[tree[i]]) {
						tree[i / 2] = tree[i];
					}
					i = i / 2;
				}
				else {
					break;
				}
			}
		}

		for (int m = 0; m < M; m++) {
			t = in.readLine ().split (" ");

			if (t[0].charAt (0) == 'W') {
				System.out.println (win ());
			}
			else if (t[0].charAt (0) == 'R') {
				replace (Integer.parseInt (t[1]) - 1, Integer.parseInt (t[2]));
			}
			else {
				System.out.println (rounds (powN - 2 + Integer.parseInt (t[1])));
			}
		}
	}
}