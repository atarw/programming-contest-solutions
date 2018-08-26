import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class NIGHTMARE_A_THON {

	static int[] ori;
	static int[][] freq;
	static int[] forw, back;

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), Q = Integer.parseInt (t[1]);
		ori = new int[N];
		forw = new int[N + 1];
		back = new int[N + 1];
		freq = new int[10][N + 1];

		t = in.readLine ().split (" ");

		for (int n = 0; n < N; n++) {
			ori[n] = Integer.parseInt (t[n]);
			forw[n + 1] = Math.max (forw[n], ori[n]);
			freq[ori[n] - 1][n + 1]++;
		}

		for (int n = N - 1; n >= 0; n--) {
			back[n] = Math.max (back[n + 1], ori[n]);
		}

		for (int r = 0; r < 10; r++) {
			for (int n = 1; n <= N; n++) {
				freq[r][n] += freq[r][n - 1];
			}

			//System.out.println (Arrays.toString (freq [r]));
		}

		int a, b, best;

		while (Q-- > 0) {
			t = in.readLine ().split (" ");
			a = Integer.parseInt (t[0]);
			b = Integer.parseInt (t[1]);
			best = Math.max (forw[a - 1], back[b]) - 1;
			//System.out.println (freq [best][N] + " " + freq [best][b] + " " + freq [best][a - 1]);
			System.out.println ((best + 1) + " " + (freq[best][N] - freq[best][b] + freq[best][a - 1]));
		}
	}
}