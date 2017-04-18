import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TSOC_16_P6_JOEY_AND_BIOLOGY {

	static int[][] cache;
	static String N, M;

	public static boolean equal (int n, int m) {
		if (N.length () - n != M.length () - m) {
			return false;
		}

		for (; n < N.length () && m < M.length (); n++, m++) {
			if (N.charAt (n) != M.charAt (m)) {
				return false;
			}
		}

		return true;
	}

	public static int edits (int n, int m) {
		int nIndex = N.length () - n, mIndex = M.length () - m;

		if (cache[nIndex][mIndex] == 0 && !equal (n, m)) {
			if (nIndex == 0 || mIndex == 0) {
				int diff = Math.abs (nIndex - mIndex);

				if (diff <= 3) {
					cache[nIndex][mIndex] = 1;
				}
				else {
					cache[nIndex][mIndex] = diff / 3;
					diff %= 3;

					cache[nIndex][mIndex] += diff / 2;
					diff %= 2;

					cache[nIndex][mIndex] += diff;
				}
			}
			else {
				int replaceCost = N.charAt (n) == M.charAt (m) ? 0 : 1;
				int min = edits (n + 1, m + 1) + replaceCost;

				if (mIndex >= 3) {
					min = Math.min (min, edits (n, m + 2) + 1);//insert 2
				}

				if (nIndex >= 3) {
					min = Math.min (min, edits (n + 2, m) + 1);//delete 2
				}

				if (mIndex >= 4) {
					min = Math.min (min, edits (n, m + 3) + 1);//insert 3
				}

				if (nIndex >= 4) {
					min = Math.min (min, edits (n + 3, m) + 1);//delete 3
				}

				min = Math.min (min, edits (n, m + 1) + 1);//insert 1
				min = Math.min (min, edits (n + 1, m) + 1);//delete 1

				cache[nIndex][mIndex] = min;
			}
		}

		return cache[nIndex][mIndex];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		in.readLine ();

		N = in.readLine ();
		M = in.readLine ();
		cache = new int[N.length () + 1][M.length () + 1];

		System.out.println (edits (0, 0));
	}
}