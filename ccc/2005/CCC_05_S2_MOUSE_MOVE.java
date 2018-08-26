import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCC_05_S2_MOUSE_MOVE {

	public static void main (String[] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		String[] t = in.readLine ().split (" ");

		int C = Integer.parseInt (t[0]), R = Integer.parseInt (t[1]);
		int X = 0, Y = 0;

		while (true) {
			t = in.readLine ().split (" ");
			int a = Integer.parseInt (t[0]), b = Integer.parseInt (t[1]);

			if (X + a > C) {
				X = C;
			}
			else if (X + a < 0) {
				X = 0;
			}
			else {
				X += a;
			}

			if (Y + b > R) {
				Y = R;
			}
			else if (Y + b < 0) {
				Y = 0;
			}
			else {
				Y += b;
			}

			if (a == 0 && b == 0) {
				break;
			}

			System.out.println (X + " " + Y);
		}
	}
}