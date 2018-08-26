import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CODEFORCES_158_B {

	public static void main (String[] t) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		int N = Integer.parseInt (in.readLine ());
		t = in.readLine ().split (" ");

		int[] grp = new int[4];

		for (int n = 0; n < N; ++n) {
			++grp[Integer.parseInt (t[n]) - 1];
		}

		//get rid of fours
		int taxis = grp[3];

		//get rid of threes
		if (grp[0] <= grp[2]) {
			taxis += grp[2];
			grp[0] = grp[2] = 0;
		}
		else {
			taxis += grp[2];
			grp[0] -= grp[2];
			grp[2] = 0;
		}

		//get rid of twos
		taxis += grp[1] / 2;
		grp[1] = grp[1] % 2;

		//get rid of ones
		if (grp[1] == 1) {
			++taxis;
			grp[1] = 0;
			grp[0] -= Math.min (grp[0], 2);
		}

		taxis += grp[0] / 4 + (grp[0] % 4 == 0 ? 0 : 1);

		System.out.print (taxis);
	}
}