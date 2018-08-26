import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_08_S5_NUKIT {

	static byte[][][][] cache;//0 = undecided, 1 = win for side to play, -1 = loss for side to play

	public static byte opp (int v) {
		return (byte) (v == -1 ? 1 : -1);
	}

	public static byte win (int A, int B, int C, int D) {
		if (cache[A][B][C][D] == 0) {
			if (A >= 2 && B >= 1 && D >= 2) {
				cache[A][B][C][D] = opp (win (A - 2, B - 1, C, D - 2));
			}

			if ((cache[A][B][C][D] == -1 || cache[A][B][C][D] == 0) && A >= 1 && B >= 1 && C >= 1 && D >= 1) {
				cache[A][B][C][D] = opp (win (A - 1, B - 1, C - 1, D - 1));
			}

			if ((cache[A][B][C][D] == -1 || cache[A][B][C][D] == 0) && C >= 2 && D >= 1) {
				cache[A][B][C][D] = opp (win (A, B, C - 2, D - 1));
			}

			if ((cache[A][B][C][D] == -1 || cache[A][B][C][D] == 0) && B >= 3) {
				cache[A][B][C][D] = opp (win (A, B - 3, C, D));
			}

			if ((cache[A][B][C][D] == -1 || cache[A][B][C][D] == 0) && A >= 1 && D >= 1) {
				cache[A][B][C][D] = opp (win (A - 1, B, C, D - 1));
			}

			if (cache[A][B][C][D] == 0) {//no reaction possible
				cache[A][B][C][D] = -1;
			}
		}

		return cache[A][B][C][D];
	}

	public static void reset () {
		cache = new byte[31][31][31][31];

		cache[0][0][0][0] = -1;
		cache[2][1][0][2] = 1;
		cache[1][1][1][1] = 1;
		cache[0][0][2][1] = 1;
		cache[0][3][0][0] = 1;
		cache[1][0][0][1] = 1;
	}

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());

		reset ();

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			System.out.println (win (Integer.parseInt (t[0]), Integer.parseInt (t[1]), Integer.parseInt (t[2]),
			                         Integer.parseInt (t[3])) == 1 ? "Patrick" : "Roland");
		}
	}
}