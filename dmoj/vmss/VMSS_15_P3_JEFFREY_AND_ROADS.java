import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VMSS_15_P3_JEFFREY_AND_ROADS {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		t = in.readLine ().split (" ");

		int xh = Integer.parseInt (t[0]), yh = Integer.parseInt (t[1]), xs = Integer.parseInt (t[2]), ys = Integer
				.parseInt (t[3]);
		int N = Integer.parseInt (in.readLine ());
		int count = 0;

		for (int n = 0; n < N; n++) {
			t = in.readLine ().split (" ");
			long A = Long.parseLong (t[0]);
			long B = Long.parseLong (t[1]);
			long C = Long.parseLong (t[2]);

			long y1 = A * xh + B * yh + C;
			long y2 = A * xs + B * ys + C;

			if (y1 < 0 && y2 > 0 || y1 > 0 && y2 < 0) {
				count++;
			}
		}

		System.out.println (count);
	}
}