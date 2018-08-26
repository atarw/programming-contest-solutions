import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// atharva washimkar
// August 11, 2018

public class MWC_15_C7_P3_BLUE_AND_GREEN {

	static int[] cache;//stores max money of each position visited -1 = unvisited, anything higher means visited
	static int N, S, L, R, I, MASK, N_MINUS_ONE;
	static int num = 0;//unique marbles

	public static void ways (int marbles, int M) {
		if (cache[marbles] < M) {//new config OR old config but new options
			//System.out.println (Integer.toBinaryString (marbles));

			if (cache[marbles] == -1) {//only increment if new config
				num++;
			}
			cache[marbles] = M;//avoid visiting unless you have more than M money

			if (M >= R) {
				ways (((marbles >>> 1) | ((marbles & (MASK >> N_MINUS_ONE)) << N_MINUS_ONE)) & MASK, M - R);//rotate
				// right
			}

			if (M >= L) {
				ways (((marbles << 1) | (marbles >> N_MINUS_ONE)) & MASK, M - L);//rotate left
			}

			if (M >= I) {
				ways (~marbles & MASK, M - I);//invert
			}

			if (M >= S) {
				for (int n = 0; n < N; n++) {
					ways (marbles ^ (1 << n), M - S);//set bit
				}
			}
		}
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		N = Integer.parseInt (t[0]);
		int M = Integer.parseInt (t[1]);
		MASK = Integer.MAX_VALUE >> (31 - N);
		N_MINUS_ONE = N - 1;
		cache = new int[1 << N];

		t = in.readLine ().split (" ");
		S = Integer.parseInt (t[0]);
		L = Integer.parseInt (t[1]);
		R = Integer.parseInt (t[2]);
		I = Integer.parseInt (t[3]);

		Arrays.fill (cache, -1);

		String m = in.readLine ();
		int start = 0;

		for (int n = 0; n < N; n++) {
			if (m.charAt (n) == 'B') {
				start ^= (1 << (N_MINUS_ONE - n));
			}
		}

		ways (start, M);
		System.out.println (num);
	}
}