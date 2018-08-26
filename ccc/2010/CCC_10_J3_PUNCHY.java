import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// atharva washimkar
// August 11, 2018

public class CCC_10_J3_PUNCHY {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t;
		int A = 0, B = 0;
		String ln;

		while (!(ln = in.readLine ()).equals ("7")) {
			t = ln.split (" ");

			if (t[0].equals ("1")) {
				if (t[1].equals ("A")) A = Integer.parseInt (t[2]);
				else B = Integer.parseInt (t[2]);
			}
			else if (t[0].equals ("2")) {
				if (t[1].equals ("A")) System.out.println (A);
				else System.out.println (B);
			}
			else if (t[0].equals ("3")) {
				int X, Y;

				if (t[1].equals ("A")) X = A;
				else X = B;

				if (t[2].equals ("A")) Y = A;
				else Y = B;

				if (t[1].equals ("A")) A = X + Y;
				else B = X + Y;
			}
			else if (t[0].equals ("4")) {
				int X, Y;

				if (t[1].equals ("A")) X = A;
				else X = B;

				if (t[2].equals ("A")) Y = A;
				else Y = B;

				if (t[1].equals ("A")) A = X * Y;
				else B = X * Y;
			}
			else if (t[0].equals ("5")) {
				int X, Y;

				if (t[1].equals ("A")) X = A;
				else X = B;

				if (t[2].equals ("A")) Y = A;
				else Y = B;

				if (t[1].equals ("A")) A = X - Y;
				else B = X - Y;
			}
			else if (t[0].equals ("6")) {
				int X, Y;

				if (t[1].equals ("A")) X = A;
				else X = B;

				if (t[2].equals ("A")) Y = A;
				else Y = B;

				if (t[1].equals ("A")) A = X / Y;
				else B = X / Y;
			}
		}
	}
}