import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class ECOO_13_R3_P4_TOUR_DE_FORCE {

	static int[][] cache;
	static int[] cards;

	public static int max (int C, int R) {
		if (cache[C][R] == 0 && !(C == cards.length)) {
			int maxIfWrong = max (C % 2 == 0 ? C + 2 : C + 1, 0) - 1;

			if (R == 9) {
				cache[C][R] = maxIfWrong;
			}
			else {
				cache[C][R] = Math.max (maxIfWrong, max (C + 1, R + 1) + cards[C]);
			}
		}

		return cache[C][R];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t;
		int N;

		for (int i = 0; i < 10; i++) {
			N = Integer.parseInt (in.readLine ());
			cache = new int[2 * N + 1][10];
			cards = new int[2 * N];

			for (int n = 0; n < N; n++) {
				t = in.readLine ().split (" ");
				cards[n * 2] = Integer.parseInt (t[0]);
				cards[n * 2 + 1] = Integer.parseInt (t[1]);
			}

			System.out.println (max (0, 0));
		}
	}
}