import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ECOO_15_R1_P2_WORD_WRAP {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));

		for (int c = 0; c < 10; c++) {
			int W = Integer.parseInt (in.readLine ());
			String ln = in.readLine ();
			String[] t = ln.split (" ");

			int count = 0;

			for (int i = 0; i < t.length; i++) {
				if (t[i].length () < W) {
					if (count + t[i].length () + 1 <= W) {
						System.out.print (t[i] + " ");
						count += t[i].length () + 1;
					}
					else {
						System.out.print ("\n" + t[i] + " ");
						count = t[i].length () + 1;
					}
				}
				else {
					System.out.print (t[i].substring (0, W - count) + "\n" + t[i].substring (W - count, t[i].length
							()));
					count = t[i].substring (W - count, t[i].length ()).length ();
				}
			}

			System.out.println ("\n=====");
		}
	}
}