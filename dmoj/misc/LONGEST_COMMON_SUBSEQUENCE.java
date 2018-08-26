import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class LONGEST_COMMON_SUBSEQUENCE {

	public static int lcs (int[] arr1, int[] arr2) {
		int[][] cache = new int[arr1.length + 1][arr2.length + 1];

		for (int x = 0; x < arr1.length; x++) {
			for (int y = 0; y < arr2.length; y++) {
				if (arr1[x] == arr2[y]) {
					cache[x + 1][y + 1] = 1 + cache[x][y];
				}
				else {
					cache[x + 1][y + 1] = Math.max (cache[x][y + 1], cache[x + 1][y]);
				}
			}
		}

		return cache[arr1.length][arr2.length];
	}

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]);
		int M = Integer.parseInt (t[1]);
		int[] arr1 = new int[N];
		int[] arr2 = new int[M];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			arr1[n] = Integer.parseInt (t[n]);
		}

		t = in.readLine ().split (" ");

		for (int m = 0; m < M; m++) {
			arr2[m] = Integer.parseInt (t[m]);
		}

		System.out.println (lcs (arr1, arr2));
	}
}