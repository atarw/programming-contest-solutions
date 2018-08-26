import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PATTERN_COUNT {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int Q = Integer.parseInt (in.readLine ());

		for (int q = 0; q < Q; ++q) {
			String n = in.readLine ();
			int cnt = 0, met = -1;

			for (int c = 0; c < n.length (); ++c) {
				if (met == -1) {
					if (n.charAt (c) == '1')
						met = c;
				}
				else if (n.charAt (c) == '1') {
					if (met != c - 1)
						++cnt;
					met = c;
				}
				else if (n.charAt (c) != '0')
					met = -1;
			}

			System.out.println (cnt);
		}
	}
}