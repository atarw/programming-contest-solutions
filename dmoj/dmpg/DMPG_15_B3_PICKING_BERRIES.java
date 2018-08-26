import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class DMPG_15_B3_PICKING_BERRIES {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");
		int W = Integer.parseInt (t[0]), H = Integer.parseInt (t[1]);
		t = null;
		String ln = "";

		int EB = 0;
		char c = ' ';

		for (int h = 0; h < H; h++) {
			ln = in.readLine ();

			for (int w = 0; w < W; w++) {
				c = ln.charAt (w);
				if (c == 'o' || c == '*') {
					if (c == 'o')
						EB++;

					c = ' ';
				}
				System.out.print (c);
			}
			System.out.println ();
		}

		for (int i = 0; i < EB; i++) {
			System.out.print ('o');
		}
	}
}