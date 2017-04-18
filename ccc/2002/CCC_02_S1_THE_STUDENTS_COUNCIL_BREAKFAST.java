import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_02_S1_THE_STUDENTS_COUNCIL_BREAKFAST {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int[] P = {Integer.parseInt (in.readLine ()), Integer.parseInt (in.readLine ()), Integer.parseInt (in.readLine
				()), Integer.parseInt (in.readLine ()), Integer.parseInt (in.readLine ())};
		int C = 0;
		int M = Integer.MAX_VALUE;

		for (int i = 0; i <= P[4] / P[0]; i++) {
			for (int x = 0; x <= P[4] / P[1]; x++) {
				for (int y = 0; y <= P[4] / P[2]; y++) {
					for (int z = 0; z <= P[4] / P[3]; z++) {
						if ((i > 0 || x > 0 || y > 0 || z > 0) && i * P[0] + x * P[1] + y * P[2] + z * P[3] == P[4]) {
							C++;
							System.out.println ("# of PINK is " + i + " # of GREEN is " + x + " # of RED is " + y + "" +
									                    " " +
									                    "# of ORANGE is " + z);

							if (i + x + y + z < M) {
								M = i + x + y + z;
							}
						}
					}
				}
			}
		}

		System.out.println ("Total combinations is " + C + ".");
		System.out.println ("Minimum number of tickets to print is " + M + ".");
	}
}