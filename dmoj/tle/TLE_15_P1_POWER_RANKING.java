import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TLE_15_P1_POWER_RANKING {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), P = Integer.parseInt (t[1]);

		People[] arr = new People[N];

		for (int n = 0; n < N; n++) {
			arr[n] = new People (in.readLine (), 0);
		}

		for (int p = 0; p < P; p++) {
			t = in.readLine ().split (" ");

			for (int n = 0; n < N; n++) {
				arr[n].t += Integer.parseInt (t[n]);
			}
		}

		Arrays.sort (arr);

		for (int i = 0; i < arr.length; i++) {
			System.out.println ((3 + i) + ". " + arr[i].n);
		}
	}

	private static class People implements Comparable<People> {

		String n;
		int t;

		People (String n, int t) {
			this.n = n;
			this.t = t;
		}

		public int compareTo (People p) {
			return p.t - t;
		}
	}
}