import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_12_J3_ICON_SCALING {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int K = Integer.parseInt (in.readLine ());
		char[][] pattern = new char[][]{{'*', 'x', '*'}, {' ', 'x', 'x'}, {'*', ' ', '*'}};

		for (int x = 0; x < 3 * K; x++) {
			for (int y = 0; y < 3 * K; y++) {
				System.out.print (pattern[x / K][y / K]);
			}
			System.out.println ();
		}
	}
}