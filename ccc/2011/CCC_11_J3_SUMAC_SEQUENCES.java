import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_11_J3_SUMAC_SEQUENCES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int[] curr = new int[2];
		curr[0] = Integer.parseInt (in.readLine ());
		curr[1] = Integer.parseInt (in.readLine ());

		int count = 2;

		while (true) {
			int sum = curr[0] - curr[1];
			count++;

			if (sum > curr[1]) {
				break;
			}

			curr[0] = curr[1];
			curr[1] = sum;
		}

		System.out.println (count);
	}
}