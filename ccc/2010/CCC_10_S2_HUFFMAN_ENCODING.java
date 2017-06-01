import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CCC_10_S2_HUFFMAN_ENCODING {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int K = Integer.parseInt (in.readLine ());
		Map<String, Character> map = new HashMap<String, Character> ();
		String[] t;

		for (int k = 0; k < K; k++) {
			t = in.readLine ().split (" ");
			map.put (t[1], t[0].charAt (0));
		}

		String seq = in.readLine ();
		String code = "";

		for (char i : seq.toCharArray ()) {
			code += i;

			if (map.containsKey (code)) {
				System.out.print (map.get (code));
				code = "";
			}
		}
	}
}