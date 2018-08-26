import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class LOWEST_EXAM_MARK {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int P = Integer.parseInt (in.readLine ());
		int Q = Integer.parseInt (in.readLine ());
		int W = Integer.parseInt (in.readLine ());

		boolean g = false;

		for (int m = 0; m <= 100; m++) {
			if ((int) (((P * (100 - W) + m * W) / 100.0) + 0.5) >= Q) {
				System.out.print (m);
				g = true;
				break;
			}
		}

		if (!g) System.out.print ("DROP THE COURSE");
	}
}