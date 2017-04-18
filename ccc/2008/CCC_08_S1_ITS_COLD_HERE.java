import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_08_S1_ITS_COLD_HERE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String city = "";
		int temp = Integer.MAX_VALUE;

		while (true) {
			String[] tokens = in.readLine ().split (" ");
			int curr = Integer.parseInt (tokens[1]);

			if (temp > curr) {
				temp = curr;
				city = tokens[0];
			}

			if (tokens[0].equals ("Waterloo")) {
				break;
			}
		}

		System.out.println (city);
	}
}