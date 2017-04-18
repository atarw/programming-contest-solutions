import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_04_J3_SMILE_WITH_SIMILES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ()), M = Integer.parseInt (in.readLine ());
		String[] nouns = new String[M];
		String[] adj = new String[N];

		for (int i = 0; i < N; i++) {
			adj[i] = in.readLine ();
		}

		for (int i = 0; i < M; i++) {
			nouns[i] = in.readLine ();
		}

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				System.out.println (adj[x] + " as " + nouns[y]);
			}
		}
	}
}