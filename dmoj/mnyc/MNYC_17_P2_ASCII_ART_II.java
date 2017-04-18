import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MNYC_17_P2_ASCII_ART_II {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out);

		t = in.readLine ().split (" ");
		int C = Integer.parseInt (t[0]), R = Integer.parseInt (t[1]);

		Map <Character, Integer> map = new HashMap <Character, Integer> ();
		char ch;

		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c) {
				ch = (char) in.read ();

				if (!Character.isWhitespace (ch)) {
					if (!map.containsKey (ch))
						map.put (ch, 0);
					map.put (ch, map.get (ch) + 1);
				}
			}
			in.read ();
		}

		int sum = 0;

		for (char k : map.keySet ()) {
			sum += map.get (k);

			if (k != '.')
				++sum;
		}

		out.println (sum);
		out.close ();
	}
}