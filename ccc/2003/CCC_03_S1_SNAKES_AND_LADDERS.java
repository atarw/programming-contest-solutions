import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CCC_03_S1_SNAKES_AND_LADDERS {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		Map <Integer, Integer> map = new HashMap <Integer, Integer> ();//square to square

		int N = -1;
		int C = 1;

		map.put (9, 34);
		map.put (54, 19);
		map.put (40, 64);
		map.put (90, 48);
		map.put (67, 86);
		map.put (99, 77);

		int sum = 0;

		while (true) {
			if (C == 100 || N == 0) {
				break;
			}

			N = Integer.parseInt (in.readLine ());

			if (100 - C >= N) {
				sum = C + N;

				if (map.containsKey (sum)) {
					C = map.get (sum);
				}
				else {
					C = sum;
				}
			}

			System.out.println ("You are now on square " + C);
		}

		if (C == 100) {
			System.out.println ("You Win!");
		}
		else {
			System.out.println ("You Quit!");
		}
	}
}