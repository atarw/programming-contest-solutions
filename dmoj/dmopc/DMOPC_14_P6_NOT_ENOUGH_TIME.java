import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMOPC_14_P6_NOT_ENOUGH_TIME {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int N = Integer.parseInt (t[0]), T = Integer.parseInt (t[1]);
		int[][] v = new int[N][3], p = new int[N][3], cache = new int[2][T + 1];

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			p[n][0] = Integer.parseInt (t[0]);
			p[n][1] = Integer.parseInt (t[2]);
			p[n][2] = Integer.parseInt (t[4]);
			v[n][0] = Integer.parseInt (t[1]);
			v[n][1] = Integer.parseInt (t[3]);
			v[n][2] = Integer.parseInt (t[5]);
		}

		int top, bot;

		for (int n = 0; n < N; n++) {
			//top = cache [(n + 1) % 2]; bot = cache [n % 2];
			top = (n + 1) % 2;
			bot = n % 2;

			for (int j = 0; j <= T; j++) {
				//top [j] = bot [j];//choose none
				cache[top][j] = cache[bot][j];

				for (int s = 0; s < 3; s++) {
					if (j >= p[n][s]) {
						//top [j] = Math.max (top [j], v [n][s] + bot [j - p [n][s]]);
						cache[top][j] = Math.max (cache[top][j], v[n][s] + cache[bot][j - p[n][s]]);
					}
				}
			}
		}

		System.out.print (cache[N % 2][T]);
	}
}